package br.com.retro.locadorajogos.controller;

import br.com.retro.locadorajogos.dto.JogoDTO;
import br.com.retro.locadorajogos.service.JogoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Tag(name = "Locadora Jogos", description = "Endpoints para gerenciamento de Locadora de Jogos")
@RestController
@RequestMapping("/jogos")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class JogoController {

    private final JogoService jogoService;

    @Operation(summary = "Cadastra um novo jogo")
    @PostMapping
    public ResponseEntity<JogoDTO> cadastrar(@RequestBody @Valid JogoDTO jogo, UriComponentsBuilder uriBuilder) {
        JogoDTO jogoCriado = jogoService.criarJogo(jogo);

        URI endereco = uriBuilder.path("/jogos/{id}").buildAndExpand(jogoCriado.getId()).toUri();

        return ResponseEntity.created(endereco).body(jogoCriado);
    }

    @Operation(summary = "Busca todos os jogos")
    @GetMapping
    public ResponseEntity<Page<JogoDTO>> buscarTodos(@PageableDefault(size = 10) Pageable paginacao) {
        return ResponseEntity.ok(jogoService.buscarTodos(paginacao));
    }

    @Operation(summary = "Busca um jogo por ID")
    @GetMapping("/{id}")
    public ResponseEntity<JogoDTO> buscaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(jogoService.buscarPorId(id));
    }

    @Operation(summary = "Atualiza um jogo por ID")
    @PutMapping("/{id}")
    public ResponseEntity<JogoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid JogoDTO dto) {
        return ResponseEntity.ok(jogoService.atualizarJogo(id, dto));
    }

    @Operation(summary = "Deleta um jogo por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        jogoService.deletarJogo(id);

        return ResponseEntity.noContent().build();
    }
}
