package com.springboot.springbootSecurity;

import com.springboot.springbootSecurity.entities.User;
import com.springboot.springbootSecurity.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootSecurityApplicationTests {

    @Autowired
    private JwtService jwtService;

	@Test
	void contextLoads() {

        User user = new User();
        user.setId(1L);
        user.setEmail("saeed@gmail.com");
        user.setPassword("123456");

        String token = jwtService.generateToken(user);
        System.out.println("Generated Token: " + token);

        Long id = jwtService.getUserIdFromToken(token);
        System.out.println("Extracted User ID from Token: " + id);
	}

}
