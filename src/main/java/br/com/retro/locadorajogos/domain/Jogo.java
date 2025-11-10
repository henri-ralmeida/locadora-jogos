package br.com.retro.locadorajogos.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Jogo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeJogo;
    private Double preco;
    @Enumerated(EnumType.STRING)
    private GeneroJogo generoJogo;
    @Enumerated(EnumType.STRING)
    private TipoMidia tipoMidia;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Boolean disponivel;
}
