package br.com.retro.locadorajogos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}

