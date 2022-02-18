package io.github.arthurgmr.service.implementation;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.arthurgmr.domain.entity.UserEntity;
import io.github.arthurgmr.domain.repository.IUserRepository;
import io.github.arthurgmr.exception.PasswordInvalidException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserDetailsService {
    
    private final IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserEntity saveUser(UserEntity dataUser) {     
        // crypt password;
        String passwordHash = passwordEncoder.encode(dataUser.getPassword());
        dataUser.setPassword(passwordHash);
        // save user;
        return userRepository.save(dataUser);
    }

    @Transactional
    public UserEntity getUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // find user;
        UserEntity user = userRepository.findByLogin(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        
        //set role;
        String[] roles = user.isAdmin() 
            ? new String[] {"USER", "ADMIN"}
            : new String[] {"USER"};
        
        return User
            .builder()
            .username(user.getLogin())
            .password(user.getPassword())
            .roles(roles)
            .build();
    }

    public UserDetails authenticate (UserEntity userAuth) {
        UserDetails userFinded = loadUserByUsername(userAuth.getLogin());
        boolean passwordMatches = passwordEncoder.matches(userAuth.getPassword(), userFinded.getPassword());
        if(passwordMatches) {
            return userFinded;
        }
        throw new PasswordInvalidException();
    }

}
