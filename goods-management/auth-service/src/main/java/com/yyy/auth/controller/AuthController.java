package com.yyy.auth.controller;

import com.yyy.auth.common.Result;
import com.yyy.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Result<String> login(@RequestParam String username, @RequestParam String password){
        return authService.login(username,password);
    }

    @PostMapping("/logout")
    public Result<Void> logout(@RequestParam String token){
        return authService.logout(token);
    }

    @GetMapping("/user/exist")
    public Result<Boolean> checkUserExist(@RequestParam Long userId){
        Result<Boolean> result = authService.checkUserExist(userId);
        boolean exist = result.getData();
        return Result.success(exist);
    }

}

