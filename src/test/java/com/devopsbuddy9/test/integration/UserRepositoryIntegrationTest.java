package com.devopsbuddy9.test.integration;

import com.devopsbuddy9.backend.persistence.domain.backend.Plan;
import com.devopsbuddy9.backend.persistence.domain.backend.Role;
import com.devopsbuddy9.backend.persistence.domain.backend.User;
import com.devopsbuddy9.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy9.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy9.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy9.backend.persistence.repositories.UserRepository;
import com.devopsbuddy9.enums.PlansEnum;
import com.devopsbuddy9.enums.RolesEnum;
import com.devopsbuddy9.utils.UserUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
;import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.devopsbuddy9.utils.UserUtils.createBasicUser;

/**
 * Created by kb on 7/20/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryIntegrationTest extends AbstractIntegrationTest {


    @Rule
    public TestName testName = new TestName();


    @Before
    public void init() {
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(planRepository);
    }

    @Test
    public void testCreateNewPlan() throws Exception {
        Plan basicPlan = createPlan(PlansEnum.BASIC);

        planRepository.save(basicPlan);

        Plan retrivedPlan = planRepository.findOne(PlansEnum.BASIC.getId());

        Assert.assertNotNull(retrivedPlan);


    }

    @Test
    public void testCreateNewRole() throws Exception {

        Role userRole = createRole(RolesEnum.BASIC);
        roleRepository.save(userRole);

        Role retrieveRole = roleRepository.findOne(RolesEnum.BASIC.getId());

        Assert.assertNotNull(retrieveRole);

    }

    @Test
    public void testDeleteUser() throws Exception {
        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@devopsbuddy.com";
        User basicUser = createBasicUser(username, email);
        userRepository.delete(basicUser.getId());
    }

    @Test
    public void createNewUser() throws Exception {

        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@devopsbuddy.com";

        User basicUser = createBasicUser(username, email);

    /*    Role basicRole = createRole(RolesEnum.BASIC);
        Set<UserRole> userRoles = new HashSet <>();
        UserRole userRole = new UserRole(basicUser, basicRole);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);

        for(UserRole ur: userRoles){
            roleRepository.save(ur.getRole());
        }*/

        basicUser = userRepository.save(basicUser);
        User newlyCreatedUser = userRepository.findOne(basicUser.getId());
        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId() != 0);
        Assert.assertNotNull(newlyCreatedUser.getPlan());
        Assert.assertNotNull(newlyCreatedUser.getPlan().getId());
        Set <UserRole> newlyCreatedUserRoles = newlyCreatedUser.getUserRoles();
        for (UserRole ur : newlyCreatedUserRoles) {
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }

    }

    @Test
    public void testGetUserByEmail() throws Exception{
        User user = createUser(testName);

        User newlyFounder = userRepository.findByEmail(user.getEmail());
        Assert.assertNotNull(newlyFounder);
        Assert.assertNotNull(newlyFounder.getId());
    }

    @Test
    public void testUpdateUserPassword() throws Exception {
        User user = createUser(testName);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());

        String newPassword = UUID.randomUUID().toString();

        userRepository.updateUserPassword(user.getId(), newPassword);

        user = userRepository.findOne(user.getId());
        Assert.assertEquals(newPassword, user.getPassword());
    }

}
