package com.jimmyss.slabel.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtToken {
    //设置过期时间
    private static final long EXPIRE_DATE=24 * 60 * 60 * 1000;
    //token秘钥
    private static final String TOKEN_SECRET = "V000w5yyWJPrxQUF8LDX";

    public static String createJwtToken(Map<String, String> claims) {
        // 获取算法实例
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

        // 创建一个JWT构造器
        var tokenBuilder = JWT.create();

        // 添加自定义声明
        claims.forEach(tokenBuilder::withClaim);

        // 设置Token的过期时间，例如2小时后过期
        tokenBuilder.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_DATE));

        // 签名并生成Token
        String token = tokenBuilder.sign(algorithm);

        return token;
    }

    public static Map<String, String> verifyJwtToken(String token){
        try {
            // 与创建Token时相同的算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();

            // 验证Token
            DecodedJWT jwt = verifier.verify(token);

            // 从Token中提取claims
            Map<String, String> claims = new HashMap<>();
            jwt.getClaims().forEach((key, value) -> claims.put(key, value.asString()));

            return claims;
        } catch (Exception exception){
            // 如果Token无效或过期，则会抛出异常
            exception.printStackTrace();
            return null;
        }
    }
}
