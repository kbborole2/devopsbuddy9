package com.devopsbuddy9.backend.service;

import com.devopsbuddy9.backend.persistence.domain.backend.PasswordResetToken;
import com.devopsbuddy9.backend.persistence.domain.backend.User;
import com.devopsbuddy9.backend.persistence.repositories.PasswordResetTokenRepository;
import com.devopsbuddy9.backend.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by kb on 7/26/2017.
 */
@Service
@Transactional
public class PasswordResetTokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Value("${token.expiration.length.minutes}")
    public int tokenExpirationInMinutes;

    private static final Logger LOG = LoggerFactory.getLogger(PasswordResetTokenService.class);

    public PasswordResetToken finByToken(String token){return passwordResetTokenRepository.findByToken(token);}

    @Transactional
    public PasswordResetToken createPasswordResetTokenForEmail(String email){
          PasswordResetToken passwordResetToken = null;

          User user = userRepository.findByEmail(email);

          if(null != user){
              String token = UUID.randomUUID().toString();
              LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
              passwordResetToken = new PasswordResetToken(token, user, now, tokenExpirationInMinutes);

              passwordResetToken = passwordResetTokenRepository.save(passwordResetToken);
              LOG.debug("Successfully created token {} for user {}", token, user.getUsername());
          } else {
              LOG.warn("We couldn't find a user for the given email {}", email);
          }
          return passwordResetToken;
    }


}
