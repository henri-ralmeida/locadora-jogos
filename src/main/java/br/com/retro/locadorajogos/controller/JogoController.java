package br.com.retro.locadorajogos.controller;

import br.com.retro.locadorajogos.dto.JogoDTO;
import br.com.retro.locadorajogos.dto.PaginaJogoResponse;
import br.com.retro.locadorajogos.service.JogoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Jogo criado com sucesso",
                    content = @Content(schema = @Schema(implementation = JogoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<JogoDTO> cadastrar(@RequestBody @Valid JogoDTO jogo, UriComponentsBuilder uriBuilder) {
        JogoDTO jogoCriado = jogoService.criarJogo(jogo);
        URI endereco = uriBuilder.path("/jogos/{id}").buildAndExpand(jogoCriado.getId()).toUri();
        return ResponseEntity.created(endereco).body(jogoCriado);
    }

    @Operation(summary = "Busca todos os jogos")
    @ApiResponse(responseCode = "200", description = "Lista de jogos retornada com sucesso")
    @GetMapping
    public ResponseEntity<PaginaJogoResponse> listarJogos(
            @RequestParam(defaultValue = "1") int page) {

        Pageable paginacao = PageRequest.of(page-1, 10, Sort.by("id").ascending());

        return ResponseEntity.ok(jogoService.buscarTodos(paginacao));
    }


    @Operation(summary = "Busca um jogo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Jogo encontrado",
                    content = @Content(schema = @Schema(implementation = JogoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Jogo não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<JogoDTO> buscaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(jogoService.buscarPorId(id));
    }

    @Operation(summary = "Atualiza um jogo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Jogo atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = JogoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Jogo não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<JogoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid JogoDTO dto) {
        return ResponseEntity.ok(jogoService.atualizarJogo(id, dto));
    }

    @Operation(summary = "Deleta um jogo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Jogo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Jogo não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        jogoService.deletarJogo(id);
        return ResponseEntity.noContent().build();
    }
}
