package com.devsuperior.hroauth.services;

import com.devsuperior.hroauth.entities.User;
import com.devsuperior.hroauth.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserFeignClient feignClient;

    public UserService(UserFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    public User findByEmail(String email) {
        User user = feignClient.findByEmail(email).getBody();
        if (Objects.isNull(user)) {
            logger.error("Email not found: {}", email);
            throw new IllegalArgumentException("Email not found");
        }
        logger.info("Email found: {}", email);
        return user;
    }
}
