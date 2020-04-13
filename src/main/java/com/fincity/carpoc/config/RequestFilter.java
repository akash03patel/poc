package com.fincity.carpoc.config;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import com.fincity.carpoc.exception.TokenException;
import com.fincity.carpoc.service.impl.CustomUserDetailService;
import com.fincity.carpoc.service.impl.UserFetchService;
import com.fincity.carpoc.util.TokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * The type Request filter.
 */
@Component
public class RequestFilter extends OncePerRequestFilter {

  /**
   * The Token util.
   */
  @Autowired
  private TokenUtil tokenUtil;

  /**
   * The Custom user detail service.
   */
  @Autowired
  private CustomUserDetailService customUserDetailService;

  /**
   * The Users service.
   */
  @Autowired
  private UserFetchService usersService;

  @Override
  protected void doFilterInternal(final HttpServletRequest request,
                                  final HttpServletResponse response,
                                  final FilterChain filterChain) throws ServletException,
      IOException {
    final String requestTokenHeader = request.getHeader("Authorization");
    String userId = null;
    String jwtToken = null;
    String url = request.getRequestURL().toString();
    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")
            && !url.contains("logout")) {
      jwtToken = requestTokenHeader.substring(7);
      try {
        userId = tokenUtil.getUserIdFromToken(jwtToken);
      } catch (IllegalArgumentException e) {
        logger.error("Unable to get Token");
        throw new TokenException("Unable to get Token");
      } catch (ExpiredJwtException e) {
        logger.error("Token has expired");
        throw new TokenException("Token expired ", e);
      } catch (SignatureException e) {
        logger.error("Authentication Failed. Username or Password not valid.");
        throw new TokenException("Authentication Failed. Username or Password not valid.", e);
      } catch (MalformedJwtException ex) {
        logger.error("Invalid JWT token");
        throw new TokenException("Invalid JWT token", ex);
      } catch (UnsupportedJwtException ex) {
        logger.error("Unsupported JWT token");
        throw new TokenException("Unsupported JWT token");
      }
    } else {
      logger.warn("Token does not begin with Bearer String");
    }
    if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      final UserDetails userDetails = customUserDetailService.loadUserByUsername(userId);
      try {
        if (Optional.ofNullable(userDetails).isPresent()) {
          if (tokenUtil.validateToken(jwtToken, userDetails)) {
            final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext()
                .setAuthentication(usernamePasswordAuthenticationToken);
          } else {
            throw new TokenException("Token expired ");
          }
        } else {
          throw new TokenException("Authentication Failed. Username or Password not valid.");
        }
      } catch (TokenException e) {
        throw new TokenException("Invalid JWT token", e);
      }
    }
    filterChain.doFilter(request, response);
  }
}

