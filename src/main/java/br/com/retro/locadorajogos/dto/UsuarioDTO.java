package br.com.retro.locadorajogos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;
    
    @NotBlank
    private String username;
    
    @NotBlank
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
}

