//package com.MyProject.Feature_Tracking_Portal.Intercepter;
//
//import com.MyProject.Feature_Tracking_Portal.utils.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class AuthenticationInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    public AuthenticationInterceptor(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // Extract token from header
//        String authHeader = request.getHeader("Authorization");
//
//        // Check if the token is present
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid token");
//            return false;
//        }
//
//        // Extract the token
//        String token = authHeader.substring(7); // Remove "Bearer " prefix
//
//        try {
//            // Validate the token
//            jwtUtil.validateToken(token);
//            return true; // Allow the request to proceed
//        } catch (Exception e) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
//            return false; // Deny the request
//        }
//
////        if (token != null && token.startsWith("Bearer ")) {
////            token = token.substring(7); // Remove "Bearer " prefix
////            if (jwtUtil.validateToken(token)) {
////                String username = jwtUtil.getUsernameFromToken(token);
////                // Add user details to the request context (e.g., for further processing)
////                request.setAttribute("username", username);
////                return true; // Token is valid, allow request to proceed
////            } else {
////                throw new InvalidTokenException("Invalid JWT Token");
////            }
////        } else {
////            throw new InvalidTokenException("Token not found or invalid");
////        }
//    }
//
//    // Handle cases where token is missing or invalid
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public static class InvalidTokenException extends RuntimeException {
//        public InvalidTokenException(String message) {
//            super(message);
//        }
//    }
//}
