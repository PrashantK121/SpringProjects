package com.example.springMVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.springMVC.model.User;
import com.example.springMVC.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityExampleApplication implements CommandLineRunner {

	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private UserRepository userRepository;
	
	@Override
    public void run(String...args) throws Exception {
       // userRepository.save(new User("admin", bCryptPasswordEncoder.encode("admin")));
    }
		
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityExampleApplication.class, args);
	}

}
