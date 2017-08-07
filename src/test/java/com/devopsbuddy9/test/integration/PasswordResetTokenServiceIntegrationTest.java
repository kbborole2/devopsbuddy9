package com.devopsbuddy9.test.integration;

import com.devopsbuddy9.backend.persistence.domain.backend.PasswordResetToken;
import com.devopsbuddy9.backend.persistence.domain.backend.User;
import com.devopsbuddy9.backend.service.PasswordResetTokenService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kb on 7/26/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PasswordResetTokenServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Rule
    public TestName testName = new TestName();

    @Test
    public void testCreateNewTokenForUserEmail() throws Exception {

        User user = createUser(testName);

        PasswordResetToken passwordResetToken =
                passwordResetTokenService.createPasswordResetTokenForEmail(user.getEmail());
        Assert.assertNotNull(passwordResetToken);
        Assert.assertNotNull(passwordResetToken.getToken());
    }


    @Test
    public void testFindByToken() throws Exception {

        User user = createUser(testName);

        PasswordResetToken passwordResetToken =
                passwordResetTokenService.createPasswordResetTokenForEmail(user.getEmail());
        Assert.assertNotNull(passwordResetToken);
        Assert.assertNotNull(passwordResetToken.getToken());

        PasswordResetToken token = passwordResetTokenService.finByToken(passwordResetToken.getToken());
        Assert.assertNotNull(token);
    }
}
