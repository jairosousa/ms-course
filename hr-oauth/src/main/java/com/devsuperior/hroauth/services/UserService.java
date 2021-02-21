package com.devsuperior.hroauth.services;

import com.devsuperior.hroauth.entities.User;
import com.devsuperior.hroauth.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserFeignClient feignClient;

    public UserService(UserFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    /**
     * Ficou afins didatico
     * em outra implementação ficar somente methodo overrride
     * @param email
     * @return
     */
    public User findByEmail(String email) {
        User user = feignClient.findByEmail(email).getBody();
        if (Objects.isNull(user)) {
            logger.error("Email not found: {}", email);
            throw new IllegalArgumentException("Email not found");
        }
        logger.info("Email found: {}", email);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = feignClient.findByEmail(username).getBody();
        if (Objects.isNull(user)) {
            logger.error("Email not found: {}", username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("Email found: {}", username);
        return user;
    }
}
