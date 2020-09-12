package com.sample.bidsystem.service;

import com.sample.bidsystem.entity.User;
import com.sample.bidsystem.message.request.LoginForm;
import com.sample.bidsystem.message.request.RegisterForm;

public interface UserService {

    public User addUser(RegisterForm registerForm);

    public String generateToken(LoginForm loginForm);

}
