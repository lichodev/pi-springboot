package com.ml.primerainfanciarest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        System.out.println("adr123" + bCryptPasswordEncoder.encode("adr123"));
        System.out.println("bel123" + bCryptPasswordEncoder.encode("bel123"));
        System.out.println("lui123" + bCryptPasswordEncoder.encode("lui123"));
    }
}
