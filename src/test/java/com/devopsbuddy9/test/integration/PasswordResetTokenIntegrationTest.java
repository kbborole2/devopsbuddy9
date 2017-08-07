package com.devopsbuddy9.test.integration;

import com.devopsbuddy9.backend.persistence.domain.backend.PasswordResetToken;
import com.devopsbuddy9.backend.persistence.domain.backend.User;
import com.devopsbuddy9.backend.persistence.repositories.PasswordResetTokenRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by kb on 7/25/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PasswordResetTokenIntegrationTest extends AbstractIntegrationTest {

    @Value("${token.expiration.length.minutes}")
    private int expirationTimeInMinutes;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Rule
    public TestName testName = new TestName();

    @Test
    public void testTokenExpirationLength() throws Exception{

        User user = createUser(testName);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());

        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
        String token = UUID.randomUUID().toString();

        LocalDateTime expectedTime = now.plusMinutes(expirationTimeInMinutes);

        PasswordResetToken passwordResetToken = createPasswordResetToken(token, user, now);

        LocalDateTime actualTime = passwordResetToken.getExpiryDate();
        Assert.assertNotNull(actualTime);
        Assert.assertEquals(actualTime,expectedTime );
    }



    @Test
    public void testDeleteToken() throws Exception {

        User user = createUser(testName);
        String token = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

        PasswordResetToken passwordResetToken = createPasswordResetToken(token, user, now);
        long tokenId = passwordResetToken.getId();
        passwordResetTokenRepository.delete(tokenId);

        PasswordResetToken shouldNotExistToken = passwordResetTokenRepository.findByToken(token);
        Assert.assertNull(shouldNotExistToken);

    }
    @Test
    public void testCascadeDeleteFromUserEntity() throws Exception{

        User user = createUser(testName);
        String token = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

        PasswordResetToken passwordResetToken = createPasswordResetToken(token, user, now);
        passwordResetToken.getId();

        userRepository.delete(user.getId());

        Set<PasswordResetToken> shouldBeEmpty = passwordResetTokenRepository.findAllByUserId(user.getId());
        Assert.assertTrue(shouldBeEmpty.isEmpty());


    }

      @Test
      public void testMultipleTokensAreReturnedWhenQueringByUserId() throws Exception{
          User user = createUser(testName);
          LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

          String token1 = UUID.randomUUID().toString();
          String token2 = UUID.randomUUID().toString();
          String token3 = UUID.randomUUID().toString();

          Set<PasswordResetToken> tokens = new HashSet <>();
          tokens.add(createPasswordResetToken(token1, user, now));
          tokens.add(createPasswordResetToken(token2, user, now));
          tokens.add(createPasswordResetToken(token3, user, now));

          passwordResetTokenRepository.save(tokens);

          User foundUser = userRepository.findOne(user.getId());

          Set<PasswordResetToken> actualTokens = passwordResetTokenRepository.findAllByUserId(foundUser.getId());

          Assert.assertTrue(actualTokens.size() == tokens.size());
          List<String> tokensAsList = tokens.stream().map(prt -> prt.getToken()).collect(Collectors.toList());
          List<String> actualTokensAsList = actualTokens.stream().map(prt -> prt.getToken()).collect(Collectors.toList());
          Assert.assertEquals(tokensAsList, actualTokensAsList);
    }




    private PasswordResetToken createPasswordResetToken(String token, User user, LocalDateTime now) {


        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user, now, expirationTimeInMinutes);
        passwordResetTokenRepository.save(passwordResetToken);
        Assert.assertNotNull(passwordResetToken.getId());
        return passwordResetToken;

    }
}
