package com.horasphere.springstarter.web.config;

import com.horasphere.shared.ddd.UUIDSequenceGenerator;
import com.horasphere.springstarter.security.application.UserApplicationService;
import com.horasphere.springstarter.security.application.impl.UserApplicationServiceImpl;
import com.horasphere.springstarter.security.domain.BasicPasswordStrengthPolicy;
import com.horasphere.springstarter.security.domain.EncryptionService;
import com.horasphere.springstarter.security.domain.UserRepository;
import com.horasphere.springstarter.security.infrastructure.PasswordEncoderEncryptionService;
import com.horasphere.springstarter.security.infrastructure.repository.mybatis.mapper.UserMapper;
import com.horasphere.springstarter.security.infrastructure.repository.mybatis.MyBatisUserRepository;
import com.horasphere.springstarter.web.rest.HomeController;
import com.horasphere.springstarter.web.rest.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Import(SecurityConfig.class)
@EntityScan(basePackages = "com.horasphere.springstarter.web.models")
@EnableJpaRepositories(basePackages = "com.horasphere.springstarter.web.models")
public class Application extends WebMvcConfigurerAdapter
{
    private final int MINIMUM_PASSWORD_LENGTH = 6;
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
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    EncryptionService encryptionService() {

        return new PasswordEncoderEncryptionService(passwordEncoder());
    }

    @Bean
    UserApplicationService userApplicationService() {
        return new UserApplicationServiceImpl(encryptionService(), userRepository(), new BasicPasswordStrengthPolicy(MINIMUM_PASSWORD_LENGTH));
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
        return new MyBatisUserRepository(userMapper(), new UUIDSequenceGenerator());
    }

    @Bean
    UserMapper userMapper() {
        return new UserMapper();
    }
}
