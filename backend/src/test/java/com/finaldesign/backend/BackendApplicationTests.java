package com.finaldesign.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("lwb"));
        System.out.println(passwordEncoder.matches("lwb", "$2a$10$XCZL7t.WPGXztepB2OUwzOSAmas2tOl810zqXD3jv40T/n1zqDlIq")); // encode():生成密文，matches():匹配明文
    }

}
