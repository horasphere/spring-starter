package com.horasphere.springstarter.web.rest;

import com.horasphere.springstarter.security.application.UserApplicationService;
import com.horasphere.springstarter.security.domain.InvalidPasswordException;
import com.horasphere.springstarter.security.domain.PasswordStrengthPolicy;
import com.horasphere.springstarter.security.domain.User;
import com.horasphere.springstarter.security.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserApplicationService userApplicationService;

    @RequestMapping("/users")
    List<User> users() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    ResponseEntity signUp(@RequestBody Map<String, Object> params) throws InvalidPasswordException {


        userApplicationService.signUp((String)params.get("email"),
        (String)params.get("password"),
        "",
        "",
        (ArrayList)params.get("roles"));

        return new ResponseEntity("OK", HttpStatus.OK);

    }
}
