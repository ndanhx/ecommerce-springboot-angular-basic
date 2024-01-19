package com.nguyenduyanh.ecommerce.filters;

import com.nguyenduyanh.ecommerce.services.jwt.UserDetailServiceImpl;
import com.nguyenduyanh.ecommerce.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailServiceImpl userDetailService;

    private final JwtUtil jwtUtil;
    private HttpServletResponse response;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token =null;
        String username = null;

           if (authHeader != null && authHeader.startsWith("Bearer ")){
               token=authHeader.substring(7);
               username=jwtUtil.extractUsername(token);
           }
           if (username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
               UserDetails userDetails = userDetailService.loadUserByUsername(username);
               if (jwtUtil.validateToken(token, userDetails)){
                   UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                           userDetails.getAuthorities());
                   authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                   SecurityContextHolder.getContext().setAuthentication(authenticationToken);

               }
           }
           filterChain.doFilter(request,response);



    }
}
