package com.horasphere.springstarter.web.config;

import com.horasphere.springstarter.security.application.UserApplicationService;
import com.horasphere.springstarter.security.application.impl.UserApplicationServiceImpl;
import com.horasphere.springstarter.security.domain.BasicPasswordStrenghtPolicy;
import com.horasphere.springstarter.security.domain.UserRepository;
import com.horasphere.springstarter.security.infrastructure.repository.mybatis.mapper.UserMapper;
import com.horasphere.springstarter.security.infrastructure.repository.mybatis.MyBatisUserRepository;
import com.horasphere.springstarter.web.rest.HomeController;
import com.horasphere.springstarter.web.rest.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@Import(SecurityConfig.class)
public class Application
{

    public static void main(String[] args) {

	int i = 0;
	while (i < 10) {
		String password = "admin";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		System.out.println(hashedPassword);
		i++;
	}
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    @Bean
    HomeController homeController() {
        return new HomeController();
    }

    @Bean
    UserController userController() {
        return new UserController();
    }

    @Bean
    UserRepository userRepository() {
        return new MyBatisUserRepository();
    }

    @Bean
    UserApplicationService userApplicationService() {
        BasicPasswordStrenghtPolicy passwordStrenghtPolicy = new BasicPasswordStrenghtPolicy(6);

        return new UserApplicationServiceImpl(passwordStrenghtPolicy);
    }

    @Bean
    UserMapper userMapper() {
        return new UserMapper();
    }
}
