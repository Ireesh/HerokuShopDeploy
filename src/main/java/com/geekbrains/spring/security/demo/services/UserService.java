package com.geekbrains.spring.security.demo.services;

import com.geekbrains.spring.security.demo.entities.Bucket;
import com.geekbrains.spring.security.demo.entities.Role;
import com.geekbrains.spring.security.demo.entities.Status;
import com.geekbrains.spring.security.demo.entities.User;
import com.geekbrains.spring.security.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", email));
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));


        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                roles);
    }

    public Page<User> findAll(Integer page) {
        if (page < 1L){
            page = 1;
        }
        return userRepository.findAll(PageRequest.of(page -1, 2));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void updateUserStatus(User user) {
        userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public User findUserByEmail(String email) { return userRepository.findUserByEmail(email); }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public boolean createNewUser(String username, String email, String password,
                                         String confirmPassword, Long phone) {
        if (username != null && email != null && password != null && confirmPassword != null && phone != null) {
            if (!password.equals(confirmPassword)) {
                return false;
            }
            if (phone.toString().length() != 11) {
                return false;
            }
            if (findUserByEmail(email) == null) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(bCryptPasswordEncoder.encode(password));
                user.setEmail(email);
                user.setPhone(phone.toString());
                user.setStatus(Status.ACTIVE);
                user.setRole(Role.USER);
                saveUser(user);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}