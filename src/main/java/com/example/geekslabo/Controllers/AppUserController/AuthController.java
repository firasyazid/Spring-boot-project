package com.example.geekslabo.Controllers.AppUserController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Authentification")
public class AuthController {
   @GetMapping("/home")
    public String Home () {
        return "this is home page " ;
    }

    @GetMapping("/admin")
    public String Admin () {
        return "this is Admin page " ;
    }

}
