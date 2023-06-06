package edu.poniperro.nowait.core.shared.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuthenticationInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper;

    public AuthenticationInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String json = request.getReader().lines().collect(Collectors.joining());
        Token token = objectMapper.readValue(json, Token.class);

        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

        if (jwtTokenProvider.validateToken(token.getToken())) {
            return true; // Continue processing the request
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // Return 403 status
            return false; // Stop processing the request
        }
    }

    /*@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Optional<Cookie> token =  Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).findFirst();
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        System.out.println("Token: ");
        if (token.isPresent() && jwtTokenProvider.validateToken(token.get().getValue())) {
            request.setAttribute("email", jwtTokenProvider.getEmailFromToken(token.get().getValue()));
            return true; // Continue processing the request
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // Return 403 status
            return false; // Stop processing the request
        }
    }*/
}




