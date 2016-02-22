package com.horasphere.springstarter.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HomeController
{
    @RequestMapping("/")
    String index(Map<String, Object> model) {
        model.put("name", "Guest");

        return "home";
    }
}
