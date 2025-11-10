package br.com.retro.locadorajogos.service;

import br.com.retro.locadorajogos.domain.Usuario;
import br.com.retro.locadorajogos.dto.UsuarioDTO;
import br.com.retro.locadorajogos.exception.SenhaIncorretaException;
import br.com.retro.locadorajogos.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final AuthenticationManager autenticador;
    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    public String autenticar(UsuarioDTO credenciais) {
        if (!usuarioService.existeUsuario(credenciais.getUsername())) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(
                            credenciais.getUsername(),
                            credenciais.getPassword()
                    );

            Authentication autenticacao = autenticador.authenticate(token);
            return tokenService.criarToken((Usuario) autenticacao.getPrincipal());

        } catch (BadCredentialsException ex) {
            throw new SenhaIncorretaException("Senha incorreta");
        }
    }
}