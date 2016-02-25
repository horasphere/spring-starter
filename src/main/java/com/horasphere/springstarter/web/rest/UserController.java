package com.horasphere.springstarter.web.rest;

import com.horasphere.springstarter.security.application.SignupCommand;
import com.horasphere.springstarter.security.application.UserApplicationService;
import com.horasphere.springstarter.security.domain.PasswordStrengthException;
import com.horasphere.springstarter.web.models.UserDTO;
import com.horasphere.springstarter.web.models.UserDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
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

    @RequestMapping("/users/{id}")
    UserDTO userById(@PathVariable("id") String id) {
        return userDTORepository.findById(id);
    }

    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    UserDTO signUp(@RequestBody Map<String, Object> params) throws PasswordStrengthException
    {
        SignupCommand signupCommand = new SignupCommand((String) params.get("email"),
            (String) params.get("password"),
            (String) params.get("firstName"),
            (String) params.get("lastName"),
            (ArrayList) params.get("roles"));

        String userId = userApplicationService.signup(signupCommand);

        return userById(userId);

    }
}
