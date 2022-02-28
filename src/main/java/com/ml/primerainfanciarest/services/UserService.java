package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.entities.UserPi;
import com.ml.primerainfanciarest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserService{

    @Autowired
    @Qualifier("UserRepository")
    private UserRepository repository;

    public UserPi getByUserName(String username) {
        return this.repository.findByUsername(username);
    }



}
