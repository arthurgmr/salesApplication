package io.github.arthurgmr.rest.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.arthurgmr.domain.entity.UserEntity;
import io.github.arthurgmr.exception.PasswordInvalidException;
import io.github.arthurgmr.rest.dto.CredentialsDTO;
import io.github.arthurgmr.rest.dto.TokenDTO;
import io.github.arthurgmr.security.JWTService;
import io.github.arthurgmr.service.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    
    // private IUserRepository userRepository;
    // public UserController(IUserRepository userRepository) {
    //     this.userRepository = userRepository;
    // }
    private final UserServiceImpl userService;
    private final JWTService JWTService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity save(@RequestBody @Valid UserEntity dataUser) {
        return userService.saveUser(dataUser);
    }

    @PostMapping("/auth")
    public TokenDTO authenticate (@RequestBody CredentialsDTO credentials) {
        try {
            UserEntity user = UserEntity.builder()
                .login(credentials.getLogin())
                .password(credentials.getPassword())
                .build();  

            userService.authenticate(user);

            String token = JWTService.genToken(user);

            return new TokenDTO(user.getLogin(), token);

        } catch (UsernameNotFoundException | PasswordInvalidException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserEntity getUserById (@PathVariable UUID id) {
        return userService
                    .getUser(id);
    }


}
