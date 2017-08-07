package com.devopsbuddy9.utils;

import com.devopsbuddy9.backend.persistence.domain.backend.User;
import com.devopsbuddy9.web.controllers.ForgotMyPasswordController;
import com.devopsbuddy9.web.domain.frontend.BasicAccountPayload;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kb on 7/21/2017.
 */
public class UserUtils {

    private UserUtils() {
        throw new AssertionError("Non instantiable");
    }


    public static User createBasicUser(String username, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword("secret");
        user.setEmail(email);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPhoneNumber("123456789023423");
        user.setCountry("SA");
        user.setEnabled(true);
        user.setDescription("a basic user");
        user.setProfileImageUrl("https://blabla.images.com/basicuser");

        return user;
    }

    public static String createPasswordResetUrl(HttpServletRequest request, long userId, String token) {
        String passwordResetUrl =
                request.getScheme() +
                        "://" +
                        request.getServerName() +
                        ":" +
                        request.getServerPort() +
                        request.getContextPath() +
                        ForgotMyPasswordController.CHANGE_PASSWORD_PATH +
                        "?id=" +
                        userId +
                        "&token=" +
                        token;

        return passwordResetUrl;
    }

    public static <T extends BasicAccountPayload> User fromWebUserToDomainUser(T frontendPayload) {
        User user = new User();
        user.setUsername(frontendPayload.getUsername());
        user.setPassword(frontendPayload.getPassword());
        user.setFirstName(frontendPayload.getFirstName());
        user.setLastName(frontendPayload.getLastName());
        user.setEmail(frontendPayload.getEmail());
        user.setPhoneNumber(frontendPayload.getPhoneNumber());
        user.setCountry(frontendPayload.getCountry());
        user.setEnabled(true);
        user.setDescription(frontendPayload.getDescription());

        return user;
    }
}
