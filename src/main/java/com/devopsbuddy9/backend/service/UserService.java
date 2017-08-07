package com.devopsbuddy9.backend.service;

import com.devopsbuddy9.backend.persistence.domain.backend.Plan;
import com.devopsbuddy9.backend.persistence.domain.backend.User;
import com.devopsbuddy9.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy9.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy9.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy9.backend.persistence.repositories.UserRepository;
import com.devopsbuddy9.enums.PlansEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;


/**
 * Created by kb on 7/21/2017.
 */
@Service
@Transactional

public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public User createUser(User user, PlansEnum plansEnum, Set<UserRole> userRoles){
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        Plan plan = new Plan(plansEnum);

        if(!planRepository.exists(plansEnum.getId())){
            plan = planRepository.save(plan);
        }

        user.setPlan(plan);

        for(UserRole ur: userRoles){
            roleRepository.save(ur.getRole());
        }

        user.getUserRoles().addAll(userRoles);

        user = userRepository.save(user);

        return user;
    }


    public User findByUserName(String username){
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }


    @Transactional
    public void updateUserPassword(long  userId, String password){
        password = passwordEncoder.encode(password);
        userRepository.updateUserPassword(userId, password);

        LOG.debug("Password update successfully for user id {} ", userId);
    }
}
