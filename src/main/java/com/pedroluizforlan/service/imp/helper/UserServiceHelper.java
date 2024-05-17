package com.pedroluizforlan.service.imp.helper;

import com.pedroluizforlan.domain.model.User;
import com.pedroluizforlan.service.exception.BusinessException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public final class UserServiceHelper {

    public void validateNewUser(User user){
        Optional.ofNullable(user)
                .orElseThrow(()-> new BusinessException("User to create most not be null"));
        Optional.ofNullable(user.getAccount())
                .orElseThrow(() -> new BusinessException("User account most not be null"));
        Optional.ofNullable(user.getCard())
                .orElseThrow(() -> new BusinessException("User card most not be null"));
    }

    public User setUserUpdate(User dbUser, User userToUpdate){
        dbUser.setName(userToUpdate.getName());
        dbUser.setAccount(userToUpdate.getAccount());
        dbUser.setCard(userToUpdate.getCard());
        dbUser.setFeatures(userToUpdate.getFeatures());
        dbUser.setNews(userToUpdate.getNews());
        return dbUser;
    }


}
