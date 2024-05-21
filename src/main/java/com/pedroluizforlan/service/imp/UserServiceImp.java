package com.pedroluizforlan.service.imp;


import com.pedroluizforlan.domain.model.User;
import com.pedroluizforlan.domain.repository.UserRepository;
import com.pedroluizforlan.service.UserService;
import com.pedroluizforlan.service.exception.BusinessException;
import com.pedroluizforlan.service.exception.NotFoundException;
import com.pedroluizforlan.service.imp.helper.UserServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
public class UserServiceImp implements UserService {

    private static final Long UNCHANGEABLE_USER_ID = 1L;
    @Autowired
    private final UserServiceHelper userServiceHelper;

    private final UserRepository userRepository;

    public UserServiceImp(UserServiceHelper userServiceHelper, UserRepository userRepository) {
        this.userServiceHelper = userServiceHelper;
        this.userRepository = userRepository;
    }


    @Transactional(readOnly = true)
    public List<User> findAll() {
        var users = userRepository.findAll();
        return users;
    }


    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
        return user;
    }

    @Transactional
    public User create(User userToCreate) {
        User user = userToCreate;
        userServiceHelper.validateNewUser(user);
        this.validateChangeableId(user.getId(), "created");
        validateCardNumberAndAccountNumber(user);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User update(Long id, User user) {
        this.validateChangeableId(id, "updated");
        User dbUser = findById(id);
        userRepository.save(userServiceHelper.setUserUpdate(dbUser, user));
        return findById(id);
    }

    @Transactional
    public void delete(Long id) {
        this.validateChangeableId(id, "deleted");
        User dbUser = findById(id);
        this.userRepository.delete(dbUser);
    }

    private void validateChangeableId(Long id, String operation) {
        if (UNCHANGEABLE_USER_ID.equals(id)) {
            throw new BusinessException("User with ID %d can not be %s.".formatted(UNCHANGEABLE_USER_ID, operation));
        }
    }

    private void validateCardNumberAndAccountNumber(User user) {
        if (userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
            throw new BusinessException("This Account number already exists");
        }
        if (userRepository.existsByCardNumber(user.getCard().getNumber())) {
            throw new BusinessException("This Card number already exists");
        }
    }


}
