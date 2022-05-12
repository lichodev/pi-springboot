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

/**
 * Servicio encargado de la lógica relacionada con los users
 * @author sole
 * @version 1.0
 */
@Service("userService")
@Transactional
public class UserService{

    @Autowired
    @Qualifier("UserRepository")
    private UserRepository repository;

    /**
     * Obtiene un user según su username
     * <p>Se utiliza para autenticación</p>
     * @param username buscado
     * @return user encontrado
     */
    public UserPi getByUserName(String username) {
        return this.repository.findByUsername(username);
    }



}
