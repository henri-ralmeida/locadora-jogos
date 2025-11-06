package br.com.retro.locadorajogos.controller;

import br.com.retro.locadorajogos.domain.Usuario;
import br.com.retro.locadorajogos.dto.UsuarioDTO;
import br.com.retro.locadorajogos.security.service.TokenService;
import br.com.retro.locadorajogos.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.HashMap;
import java.util.Map;

@Tag(name = "Autenticacao Locadora", description = "Endpoints para gerenciamento de Usuários")
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager autenticador;
    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    @Operation(summary = "Autentica um Usuário")
    @SecurityRequirement(name = "bearer-key")
    @PostMapping
    public ResponseEntity<Object> validacaoUsuario(@RequestBody @Valid UsuarioDTO credenciais) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                credenciais.getUsername(), credenciais.getPassword());

        Authentication autenticacao = autenticador.authenticate(token);

        return ResponseEntity.ok(tokenService.criarToken((Usuario) autenticacao.getPrincipal()));
    }

    @Operation(summary = "Cria um novo Usuário")
    @PostMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> criacaoUsuario(
            @RequestBody @Valid UsuarioDTO credenciais, 
            UriComponentsBuilder uriBuilder) {
        
        UsuarioDTO usuarioCriado = usuarioService.criarUsuario(credenciais);

        Authentication authentication = autenticador.authenticate(
            new UsernamePasswordAuthenticationToken(
                credenciais.getUsername(), 
                credenciais.getPassword()
            )
        );

        String token = tokenService.criarToken((Usuario) authentication.getPrincipal());

        Map<String, Object> response = new HashMap<>();
        response.put("usuario", usuarioCriado);
        response.put("token", token);
        
        URI endereco = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioCriado.getId()).toUri();
        
        return ResponseEntity.created(endereco).body(response);
    }
}