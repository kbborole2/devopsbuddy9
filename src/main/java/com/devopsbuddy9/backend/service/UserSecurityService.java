package com.devopsbuddy9.backend.service;

import com.devopsbuddy9.backend.persistence.domain.backend.User;
import com.devopsbuddy9.backend.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by kb on 7/21/2017.
 */
@Service
public class UserSecurityService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (null == user){
            LOG.warn("Username {} not found", username);
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

        return user;
    }
}
