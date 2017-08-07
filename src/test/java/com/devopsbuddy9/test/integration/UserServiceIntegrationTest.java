package com.devopsbuddy9.test.integration;

import com.devopsbuddy9.backend.persistence.domain.backend.Role;
import com.devopsbuddy9.backend.persistence.domain.backend.User;
import com.devopsbuddy9.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy9.backend.service.UserService;
import com.devopsbuddy9.enums.PlansEnum;
import com.devopsbuddy9.enums.RolesEnum;
import com.devopsbuddy9.utils.UserUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kb on 7/21/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest extends AbstractIntegrationTest{

    @Autowired
    protected UserService userService;

    @Rule
    public TestName testName = new TestName();

    @Test
    public void testCreateNewUser() throws Exception{

        User user = createUser(testName);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
    }


    public User createUser(TestName testName){

        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@devopsbuddy.com";

        Set<UserRole> userRoles = new HashSet <>();
        User basicUser = UserUtils.createBasicUser(username, email);
        userRoles.add(new UserRole(basicUser, new Role(RolesEnum.BASIC)));

        return userService.createUser(basicUser, PlansEnum.BASIC, userRoles);
    }

}
