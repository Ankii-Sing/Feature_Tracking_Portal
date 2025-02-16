//package com.MyProject.Feature_Tracking_Portal.Interceptor;
//import com.MyProject.Feature_Tracking_Portal.utils.JwtService;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.security.SignatureException;
//
//@Component
//@Slf4j
//public class JwtInterceptor implements HandlerInterceptor {
//
//    private final JwtService jwtUtil;
//
//    public JwtInterceptor(JwtService jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//            log.error("Missing or invalid Authorization header");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Missing or invalid Authorization header");
//            return false;
//        }
//
//        String token = authorizationHeader.substring(7);
//
//        try {
//            String email = jwtUtil.extractEmail(token);
//            if (email == null || jwtUtil.isTokenExpired(token)) {
//                log.error("Invalid or expired token for email: {}", email);
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.getWriter().write("Invalid or expired token");
//                return false;
//            }
//        } catch (ExpiredJwtException e) {
//            log.error("Expired token: {}", e.getMessage());
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Invalid or expired token");
//            return false;
//        } catch (MalformedJwtException e) {
//            log.error("Malformed or tampered token: {}", e.getMessage());
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Invalid or tampered token");
//            return false;
//        }
//
//        log.info("Token is valid");
//        return true;
//    }
//}