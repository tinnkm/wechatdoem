package com.tinnkm.application.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinnkm.application.model.User;
import com.tinnkm.application.util.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;

/**
 * @author quantdo_wh02
 * @version 1.0
 * @classname JwtLoginFilter
 * @description jwt下发token
 * @date 2018/6/20 15:38
 **/
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final Logger log = LoggerFactory.getLogger(JwtLoginFilter.class);
    private AuthenticationManager authenticationManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)  {
        User user = null;
        try (InputStream inputStream = request.getInputStream()) {
            user = new ObjectMapper().readValue(inputStream, User.class);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
        if (null == user) {
            log.error("can't get user info");
            return null;
        }
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getName(),
                        user.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JwtUtils.getToken(((User) authResult.getPrincipal()).getName(), Date.from(Instant.now().plus(30, ChronoUnit.MINUTES)));
        response.addHeader("Authorization", JwtUtils.AUTHORIZATIONHEADERPREFIX + token);
        // 对于学校的系统只做了前端鉴权
        response.addHeader("roles", ((User) authResult.getPrincipal()).getRoles());
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
