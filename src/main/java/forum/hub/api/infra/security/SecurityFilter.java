package forum.hub.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import forum.hub.api.domain.user.UserRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        var JWTToken = getToken(request);

        if(JWTToken != null) {
            var subject = tokenService.getSubject(JWTToken);

            var user = repository.findByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        filterChain.doFilter(request, response);
    }
        
    private String getToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null) {
            throw new RuntimeException("Token não encontrado");
        }
        return authorizationHeader.substring("Bearer ".length());
    }
    
}
