package com.example.demojwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

@Slf4j
//@SpringBootTest
class DemoJwtApplicationTests {

    @Test
    void contextLoads() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, 90);
        //生成令牌
        String token = JWT.create()
                .withClaim("username", "张三") //设置自定义用户名
                .withExpiresAt(instance.getTime()) //设置过期时间
                .sign(Algorithm.HMAC256("Q2W#E$RW"));//设置签名保密复杂/ /输出令牌
        log.info(token);
    }

    @Test
    void test02() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTgwOTE1MzUsInVzZXJuYW1lIjoi5byg5LiJIn0.tyoqVCTnPLe3t9O3ZscgigeyNlRJ4k8VypszIOP7vIw";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("Q2W#E$RW")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        log.info("用户名:{}", decodedJWT.getClaim("username").asString());
        log.info("过期时间: " + decodedJWT.getExpiresAt());
    }

}
