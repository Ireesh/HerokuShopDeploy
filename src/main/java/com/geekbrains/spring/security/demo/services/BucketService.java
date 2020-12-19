package com.geekbrains.spring.security.demo.services;

import com.geekbrains.spring.security.demo.dto.BucketDetailDto;
import com.geekbrains.spring.security.demo.dto.BucketDto;
import com.geekbrains.spring.security.demo.dto.ProductDto;
import com.geekbrains.spring.security.demo.entities.Bucket;
import com.geekbrains.spring.security.demo.entities.Product;
import com.geekbrains.spring.security.demo.entities.User;
import com.geekbrains.spring.security.demo.mappers.ProductMapper;
import com.geekbrains.spring.security.demo.repositories.BucketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BucketService {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    BucketRepository bucketRepository;
    private final ProductMapper mapper = ProductMapper.MAPPER;
    private final SimpMessagingTemplate template;

    public BucketService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public BucketDto findBucketByUser(User user) {
        BucketDto bucketDto = new BucketDto();
        Map<Long, BucketDetailDto> mapByProductId = new HashMap<>();

        List<Product> products = user.getBucket().getProducts();
        for (Product product : products) {
            BucketDetailDto detail = mapByProductId.get(product.getId());
            if(detail == null){
                mapByProductId.put(product.getId(), new BucketDetailDto(product));
            }
            else {
                detail.setAmount(detail.getAmount() + 1);
                detail.setSum(detail.getSum().add(detail.getPrice()));
            }
        }

        bucketDto.setBucketDetails(new ArrayList<>(mapByProductId.values()));
        bucketDto.aggregate();
        return bucketDto;
    }

    public List<Product> productsBucket(Bucket bucket) {
        return bucket.getProducts();
    }


    public void createNewBucket(User user) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        bucketRepository.save(bucket);
    }


    public void addProductToBucket(ProductDto productDto, String email) {
        Bucket bucket = bucketRepository.findBucketByUser(userService.findUserByEmail(email));
        List<Product> products = bucket.getProducts();
        products.add(mapper.toProduct(productDto));
        bucket.setProducts(products);
        bucketRepository.save(bucket);

        template.convertAndSend("/topic/auth/profile/bucket",
                productDto);
    }


}
