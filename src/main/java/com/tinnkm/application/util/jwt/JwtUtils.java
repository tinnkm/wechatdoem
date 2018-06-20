package com.tinnkm.application.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @author quantdo_wh02
 * @version 1.0
 * @classname JwtUtil
 * @description jwt工具类
 * @date 2018/6/20 16:22
 **/
public class JwtUtils {
    public static final  String AUTHORIZATIONHEADERPREFIX = "Bearer ";
    private static String key = "tinnkm";
    private JwtUtils() {
    }

    public static void setKey(String key){
        JwtUtils.key = key;
    }
    public static String getToken(String subject,Date expires) {
        Algorithm algorithm = Algorithm.HMAC512(key);
        return JWT.create()
                .withSubject(subject)
                .withExpiresAt(expires)
                .sign(algorithm);
    }

    public static boolean verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512(JwtUtils.key);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            verifier.verify(token.substring(JwtUtils.AUTHORIZATIONHEADERPREFIX.length()));
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
    public static DecodedJWT decodeToken(String token) {
        return JWT.decode(token.substring(JwtUtils.AUTHORIZATIONHEADERPREFIX.length()));
    }

}
