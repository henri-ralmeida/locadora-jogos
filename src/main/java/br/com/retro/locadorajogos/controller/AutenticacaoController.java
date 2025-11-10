package br.com.retro.locadorajogos.controller;

import br.com.retro.locadorajogos.dto.UsuarioDTO;
import br.com.retro.locadorajogos.service.AutenticacaoService;
import br.com.retro.locadorajogos.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Tag(name = "Autenticação Locadora", description = "Endpoints para login e criação de usuários")
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;
    private final UsuarioService usuarioService;

    @Operation(summary = "Autentica um usuário", description = "Realiza o login do usuário e retorna um token JWT de autenticação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Senha incorreta"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PostMapping
    public ResponseEntity<Object> login(@RequestBody @Valid UsuarioDTO credenciais) {
        String token = autenticacaoService.autenticar(credenciais);
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Cria um novo usuário", description = "Registra um novo usuário no sistema de locadora de jogos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Usuário já existente")
    })
    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody @Valid UsuarioDTO credenciais,
                                                   UriComponentsBuilder uriBuilder) {

        UsuarioDTO usuarioCriado = usuarioService.criarUsuario(credenciais);
        URI endereco = uriBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuarioCriado.getId())
                .toUri();

        return ResponseEntity.created(endereco).body(usuarioCriado);
    }
}