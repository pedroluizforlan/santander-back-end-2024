package com.pedroluizforlan.service;

import com.pedroluizforlan.domain.model.User;

public interface UserService {

    User findById(Long id);

    User create(User user);


}
