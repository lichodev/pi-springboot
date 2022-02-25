package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.UserPi;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<UserPi, Serializable> {

    public abstract UserPi findByUsername(String username);
}
