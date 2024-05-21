package com.pedroluizforlan.service.imp.helper;


import com.pedroluizforlan.domain.model.User;
import com.pedroluizforlan.service.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public final class UserServiceHelper {

    public void validateNewUser(User user) {
        Optional.ofNullable(user)
                .orElseThrow(() -> new BusinessException("User to create most not be null"));
        Optional.ofNullable(user.getAccount())
                .orElseThrow(() -> new BusinessException("User account most not be null"));
        Optional.ofNullable(user.getCard())
                .orElseThrow(() -> new BusinessException("User card most not be null"));
    }

    public User setUserUpdate(User dbUser, User userToUpdate) {
        User user = userToUpdate;
        user.setId(dbUser.getId());
        user.getAccount().setId(dbUser.getAccount().getId());
        user.getCard().setId(dbUser.getCard().getId());
        return user;
    }
}
