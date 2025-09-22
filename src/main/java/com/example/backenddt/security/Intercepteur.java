//package com.example.backenddt.security;
//
//import com.example.backenddt.services.UserService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//@Component
//public class Intercepteur extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtGestion jwtGestion;
//
//    @Autowired
//    private UserDetailsService userService;
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain)
//            throws ServletException, IOException
//    {
//        final String authHeader = request.getHeader("Authorization");
//        String email = null;
//        String jwt = null;
//        if (authHeader != null && authHeader.startsWith("Bearer ")){
//            jwt = authHeader.substring(7);
//            email = jwtGestion.extraireUser(jwt);
//        }
//
//        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails = userService.loadUserByUsername(email);
//            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(userAuth);
//        }
//
//        filterChain.doFilter(request,response);
//    }
//}
