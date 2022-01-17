package io.github.arthurgmr.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.arthurgmr.domain.entity.UserEntity;
import io.github.arthurgmr.domain.repository.IUserRepository;

@Service
public class IUserServiceImpl implements UserDetailsService {
    
    @Autowired
    private IUserRepository userRepository;

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

}
