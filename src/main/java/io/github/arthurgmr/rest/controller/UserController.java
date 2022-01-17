package io.github.arthurgmr.rest.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.arthurgmr.domain.entity.UserEntity;
import io.github.arthurgmr.domain.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    
    // private IUserRepository userRepository;
    // public UserController(IUserRepository userRepository) {
    //     this.userRepository = userRepository;
    // }
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity saveUser(@RequestBody @Valid UserEntity dataUser) {
        // crypt password;
        String passwordHash = passwordEncoder.encode(dataUser.getPassword());
        dataUser.setPassword(passwordHash);
        // save user;
        UserEntity user = userRepository.save(dataUser);
        return user;
    }


}
