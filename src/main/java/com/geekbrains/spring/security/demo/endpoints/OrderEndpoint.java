package com.geekbrains.spring.security.demo.endpoints;

import com.geekbrains.spring.security.demo.entities.Order;
import com.geekbrains.spring.security.demo.services.OrderService;
import com.geekbrains.spring.security.demo.ws.order.GetOrderResponse;
import com.geekbrains.spring.security.demo.ws.order.OrderWS;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class OrderEndpoint {

    public static final String NAMESPACE_URL = "http://geerbrains.com/spring/security/demo/ws/order";

    private OrderService orderService;

    public OrderEndpoint(OrderService orderService) {
        this.orderService = orderService;
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "getOrderRequest")
    @ResponsePayload
    public GetOrderResponse getOrder(String userName)
            throws DatatypeConfigurationException {
        GetOrderResponse response = new GetOrderResponse();
        orderService.findAllOrdersByUser(userName)
                .forEach(order -> response.getOrder().add(createOrderWS(order)));
        return response;
    }

    private OrderWS createOrderWS(Order order){
        OrderWS ws = new OrderWS();
        ws.setId(order.getId());
        ws.setTotalPrice(String.valueOf(order.getTotalPrice()));
        ws.setCondition(String.valueOf(order.getCondition()));
        return ws;
    }
}
