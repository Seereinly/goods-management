package com.yyy.auth.service.impl;

import com.yyy.auth.common.Result;
import com.yyy.auth.entity.User;
import com.yyy.auth.mapper.UserMapper;
import com.yyy.auth.service.AuthService;
import com.yyy.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<String> login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if(user == null){
            return Result.fail("账号不存在");
        }
        if(!user.getPassword().equals(password)){
            return Result.fail("密码错误");
        }
        // 直接返回token字符串，不再包装TokenVO对象
        String token = JwtUtil.generateToken(user.getId());
        return Result.success(token);
    }

    @Override
    public Result<Void> logout(String token) {
        // 无Redis，仅占位，前端清除本地token；token等待自动过期
        return Result.success(null);
    }

    @Override
    public Result<Boolean> checkUserExist(Long userId) {
        User user = userMapper.selectById(userId);
        return Result.success(user != null);
    }

}
