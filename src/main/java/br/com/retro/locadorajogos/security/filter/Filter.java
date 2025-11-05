package br.com.retro.locadorajogos.security.filter;

import br.com.retro.locadorajogos.security.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Filter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = buscarToken(request);
        String usuarioLogin = tokenService.buscarUsuarioToken(token);

        filterChain.doFilter(request, response);
    }

    private String buscarToken(HttpServletRequest request){
        var authorization = request.getHeader("Authorization");

        if(authorization == null || authorization.isBlank()) {
            throw new RuntimeException("Token não encontrado!");
        }

        return authorization.replace("Bearer ", "");
    }
}
