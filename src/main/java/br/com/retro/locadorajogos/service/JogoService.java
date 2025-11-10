package br.com.retro.locadorajogos.service;

import br.com.retro.locadorajogos.domain.Jogo;
import br.com.retro.locadorajogos.dto.JogoDTO;
import br.com.retro.locadorajogos.dto.PaginaJogoResponse;
import br.com.retro.locadorajogos.exception.NenhumJogoEncontradoException;
import br.com.retro.locadorajogos.repository.JogoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JogoService {

    private final JogoRepository jogoRepository;
    private final ModelMapper modelMapper;

    public JogoDTO criarJogo(JogoDTO dto) {
        try {
            Jogo jogo = modelMapper.map(dto, Jogo.class);
            jogoRepository.save(jogo);
            return modelMapper.map(jogo, JogoDTO.class);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Dados inválidos para criação do jogo");
        }
    }

    public PaginaJogoResponse buscarTodos(Pageable paginacao) {
        Page<JogoDTO> pagina = jogoRepository.findAll(paginacao)
                .map(jogo -> modelMapper.map(jogo, JogoDTO.class));

        if (pagina.getContent().isEmpty()) {
            throw new NenhumJogoEncontradoException("Nenhum jogo encontrado nesta página");
        }

        return new PaginaJogoResponse(
                pagina.getContent(),
                pagina.getNumber(),
                pagina.getTotalPages(),
                pagina.getTotalElements()
        );
    }

    public JogoDTO buscarPorId(Long id) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jogo não encontrado"));
        return modelMapper.map(jogo, JogoDTO.class);
    }

    public JogoDTO atualizarJogo(Long id, JogoDTO dto) {
        Jogo jogoExistente = jogoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jogo não encontrado"));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(JogoDTO.class, Jogo.class).addMappings(
                mapper -> mapper.skip(Jogo::setId));
        modelMapper.map(dto, jogoExistente);

        Jogo jogoAtualizado = jogoRepository.save(jogoExistente);

        return modelMapper.map(jogoAtualizado, JogoDTO.class);
    }

    public void deletarJogo(Long id) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jogo não encontrado"));
        jogoRepository.delete(jogo);
    }
}
