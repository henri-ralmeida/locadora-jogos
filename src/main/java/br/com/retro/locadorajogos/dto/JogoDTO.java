package br.com.retro.locadorajogos.dto;

import br.com.retro.locadorajogos.domain.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
public class JogoDTO {
    private Long id;
    @NotBlank
    private String jogo;
    @Positive
    private Double preco;
    private GeneroJogo generoJogo;
    private TipoMidia tipoMidia;
    private Categoria categoria;
    @AssertTrue
    @AssertFalse
    private Boolean disponivel;
}
