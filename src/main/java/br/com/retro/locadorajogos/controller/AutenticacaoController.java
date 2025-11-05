package br.com.retro.locadorajogos.controller;

import br.com.retro.locadorajogos.domain.Usuario;
import br.com.retro.locadorajogos.dto.UsuarioDTO;
import br.com.retro.locadorajogos.security.service.TokenService;
import br.com.retro.locadorajogos.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager autenticador;
    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<Object> validacaoUsuario(@RequestBody @Valid UsuarioDTO credenciais) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                credenciais.getUsername(), credenciais.getPassword());

        Authentication autenticacao = autenticador.authenticate(token);

        return ResponseEntity.ok(tokenService.criarToken((Usuario) autenticacao.getPrincipal()));
    }

    @SecurityRequirement(name = "bearer-key")
    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioDTO> criacaoUsuario(@RequestBody @Valid UsuarioDTO credenciais, UriComponentsBuilder uriBuilder) {
        UsuarioDTO usuarioCriado = usuarioService.criarUsuario(credenciais);

        URI endereco = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioCriado.getId()).toUri();

        return ResponseEntity.created(endereco).body(usuarioCriado);
    }
}
