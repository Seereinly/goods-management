package com.yyy.auth.mapper;

import com.yyy.auth.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectByUsername(@Param("username") String username);
    User selectById(@Param("id") Long id);

}
