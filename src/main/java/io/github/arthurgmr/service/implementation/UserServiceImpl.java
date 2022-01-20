package io.github.arthurgmr.service.implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.arthurgmr.domain.entity.UserEntity;
import io.github.arthurgmr.domain.repository.IUserRepository;
import io.github.arthurgmr.exception.PasswordInvalidException;

@Service
public class UserServiceImpl implements UserDetailsService {
    
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserEntity saveAndCryptPass(UserEntity dataUser) {
        // crypt password;
        String passwordHash = passwordEncoder.encode(dataUser.getPassword());
        dataUser.setPassword(passwordHash);
        // save user;
        UserEntity user = userRepository.save(dataUser);
        return user;
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
