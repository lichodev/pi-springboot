package com.ml.primerainfanciarest.security.service;

import com.ml.primerainfanciarest.entities.UserPi;
import com.ml.primerainfanciarest.security.models.UserPrincipal;
import com.ml.primerainfanciarest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService service;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPi user = this.service.getByUserName(username);

        return UserPrincipal.build(user);
    }

}
