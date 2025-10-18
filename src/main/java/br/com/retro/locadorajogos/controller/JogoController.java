package br.com.retro.locadorajogos.controller;

import br.com.retro.locadorajogos.dto.JogoDTO;
import br.com.retro.locadorajogos.service.JogoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
@RequiredArgsConstructor
public class JogoController {

    private final JogoService jogoService;

    @PostMapping
    public void cadastrar(@RequestBody @Valid JogoDTO dto) {
        jogoService.criarJogo(dto);
    }

    @GetMapping
    public ResponseEntity<List<JogoDTO>> buscarTodos() {
        return ResponseEntity.ok(jogoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public JogoDTO buscaPorId(@PathVariable Long id){
        return jogoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public JogoDTO atualizar(@PathVariable Long id, @RequestBody @Valid JogoDTO dto){
        return jogoService.atualizarJogo(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        jogoService.deletarJogo(id);
    }
}
