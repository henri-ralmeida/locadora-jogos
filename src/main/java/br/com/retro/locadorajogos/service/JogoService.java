package br.com.retro.locadorajogos.service;

import br.com.retro.locadorajogos.domain.Jogo;
import br.com.retro.locadorajogos.dto.JogoDTO;
import br.com.retro.locadorajogos.repository.JogoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogoService {
    private final JogoRepository jogoRepository;
    private final ModelMapper modelMapper;

    public JogoDTO criarJogo(JogoDTO dto) {
        Jogo jogo = modelMapper.map(dto, Jogo.class);
        jogoRepository.save(jogo);

        return modelMapper.map(jogo, JogoDTO.class);
    }

    public List<JogoDTO> buscarTodos() {
        return jogoRepository.findAll().stream().map(
                j -> modelMapper.map(j, JogoDTO.class)).toList();
    }

    public JogoDTO buscarPorId(Long id) {
        Jogo jogo = jogoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Jogo não encontrado"));

        return modelMapper.map(jogo, JogoDTO.class);
    }

    public JogoDTO atualizarJogo(Long id, JogoDTO dto) {
        Jogo jogo = modelMapper.map(dto, Jogo.class);
        jogo.setId(id);
        jogo = jogoRepository.save(jogo);

        return modelMapper.map(jogo, JogoDTO.class);
    }

    public void deletarJogo(Long id) {
        jogoRepository.deleteById(id);
    }
}
