package com.horasphere.springstarter.web.rest;

import com.horasphere.springstarter.security.application.SignupCommand;
import com.horasphere.springstarter.security.application.UserApplicationService;
import com.horasphere.springstarter.security.domain.PasswordStrengthException;
import com.horasphere.springstarter.web.models.UserDTO;
import com.horasphere.springstarter.web.models.UserDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    UserDTORepository userDTORepository;

    @Autowired
    UserApplicationService userApplicationService;

    @RequestMapping("/users")
    List<UserDTO> users() {
        return new ArrayList(userDTORepository.findAll());
    }

    @RequestMapping("/users/email")
    UserDTO users(@RequestParam(value = "email", required = true) String email) {
        System.out.println("Email: " + email);
        return userDTORepository.findByEmail(email);
    }

    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    ResponseEntity signUp(@RequestBody Map<String, Object> params) throws PasswordStrengthException
    {
        SignupCommand signupCommand = new SignupCommand((String) params.get("email"),
            (String) params.get("password"),
            (String) params.get("firstName"),
            (String) params.get("lastName"),
            (ArrayList) params.get("roles"));

        userApplicationService.signup(signupCommand);

        return new ResponseEntity("OK", HttpStatus.OK);

    }
}
