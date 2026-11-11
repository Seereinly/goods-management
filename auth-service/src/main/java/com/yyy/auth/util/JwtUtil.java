package com.yyy.auth.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    // 密钥！gateway网关JwtUtil必须完全一致
    private static final String SECRET = "abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmn";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    // token有效期：2小时
    private static final long EXPIRATION = 2 * 60 * 60 * 1000L;

    /**
     * 生成token
     */
    public static String generateToken(Long userId) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + EXPIRATION);
        return Jwts.builder()
                .subject(userId.toString())
                .claim("userId", userId)
                .issuedAt(now)
                .expiration(expireDate)
                .signWith(KEY, Jwts.SIG.HS256)
                .compact();
    }

    /**
     * 解析token获取载荷
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
