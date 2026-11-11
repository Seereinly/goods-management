package com.yyy.auth.service;

import com.yyy.auth.common.Result;

public interface AuthService {
    Result<String> login(String username, String password);
    Result<Void> logout(String token);
    Result<Boolean> checkUserExist(Long userId);
}
