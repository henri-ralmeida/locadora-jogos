package br.com.retro.locadorajogos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginaJogoResponse {
    private List<JogoDTO> jogos;
    private int paginaAtual;
    private int totalPaginas;
    private long totalJogos;
}
