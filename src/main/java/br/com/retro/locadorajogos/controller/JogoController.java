package br.com.retro.locadorajogos.controller;

import br.com.retro.locadorajogos.dto.JogoDTO;
import br.com.retro.locadorajogos.service.JogoService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogos")
@RequiredArgsConstructor
public class JogoController {

    private final JogoService jogoService;

    @PostMapping
    public ResponseEntity<JogoDTO> cadastrar(@RequestBody @Valid JogoDTO dto) {
        return ResponseEntity.ok(jogoService.criarJogo(dto));
    }

    @GetMapping
    public ResponseEntity<Page<JogoDTO>> buscarTodos(@PageableDefault(size = 10) Pageable paginacao) {
        return ResponseEntity.ok(jogoService.buscarTodos(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogoDTO> buscaPorId(@PathVariable Long id){
        return ResponseEntity.ok(jogoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid JogoDTO dto){
        return ResponseEntity.ok(jogoService.atualizarJogo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        jogoService.deletarJogo(id);
        return ResponseEntity.noContent().build();
    }
}
