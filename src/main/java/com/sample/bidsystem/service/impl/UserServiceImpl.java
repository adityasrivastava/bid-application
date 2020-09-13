package com.sample.bidsystem.service.impl;

import com.sample.bidsystem.entity.Role;
import com.sample.bidsystem.entity.RoleType;
import com.sample.bidsystem.entity.User;
import com.sample.bidsystem.model.request.LoginForm;
import com.sample.bidsystem.model.request.RegisterForm;
import com.sample.bidsystem.repository.RoleRepository;
import com.sample.bidsystem.repository.UserRepository;
import com.sample.bidsystem.security.jwt.JwtTokenProviderService;
import com.sample.bidsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProviderService jwtTokenProviderService;

    @Override
    public User addUser(RegisterForm registerForm) {

        User user;
        Set<Role> roles = new HashSet<>();

        Set<String> userRoles = registerForm.getRole();

        user = new User(registerForm.getName(), registerForm.getUsername(),
                registerForm.getEmail(), encoder.encode(registerForm.getPassword()),roles);

        userRoles.forEach(role -> {
            switch(role) {
                case "seller":
                    Role seller = roleRepository.findByName(RoleType.ROLE_SELLER)
                            .orElseThrow(() -> new RuntimeException("User Role not find."));

                    roles.add(seller);

                case "buyer":
                    Role buyer = roleRepository.findByName(RoleType.ROLE_BUYER)
                            .orElseThrow(() -> new RuntimeException("User Role not find."));
                    roles.add(buyer);

                default:

            }
        });

        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("User Role not find."));
        roles.add(userRole);

        user.setRoles(roles);

        return userRepository.save(user);

    }

    @Override
    public String generateToken(LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProviderService.generateJwtToken(authentication);
    }
}
