package com.aegiscard.infrastructure.logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();

        // Captura body da requisição
        String body = "";
        if ("POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod())) {
            try (BufferedReader reader = request.getReader()) {
                body = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }

        logger.info("➡️ Request: {} {} from IP {} Body: {}",
                request.getMethod(), request.getRequestURI(), request.getRemoteAddr(), body);

        // Usa wrapper para capturar resposta
//        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
//        filterChain.doFilter(request, wrappedResponse);
//
//        long duration = System.currentTimeMillis() - startTime;
//
//        String responseBody = new String(wrappedResponse.getContentAsByteArray(), response.getCharacterEncoding());
//        logger.info("⬅️ Response: {} {} (status: {}, duration: {} ms) Body: {}",
//                request.getMethod(), request.getRequestURI(), wrappedResponse.getStatus(), duration, responseBody);
//
//        wrappedResponse.copyBodyToResponse(); // devolve resposta original ao cliente
    }
}
