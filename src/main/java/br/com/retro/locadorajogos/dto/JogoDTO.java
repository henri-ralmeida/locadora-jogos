package br.com.retro.locadorajogos.dto;

import br.com.retro.locadorajogos.domain.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
public class JogoDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank
    private String nomeJogo;
    @Positive
    private Double preco;
    private GeneroJogo generoJogo;
    private TipoMidia tipoMidia;
    private Categoria categoria;
    private Boolean disponivel = true;
}
