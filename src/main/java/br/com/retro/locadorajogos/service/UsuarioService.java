package br.com.retro.locadorajogos.service;

import br.com.retro.locadorajogos.domain.Usuario;
import br.com.retro.locadorajogos.dto.UsuarioDTO;
import br.com.retro.locadorajogos.exception.UsuarioJaExisteException;
import br.com.retro.locadorajogos.repository.UsuarioRepository;
import br.com.retro.locadorajogos.security.CriptografiaSenha;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return usuario;
    }

    public UsuarioDTO criarUsuario(UsuarioDTO credenciais) {
        if (usuarioRepository.existsByUsername(credenciais.getUsername())) {
            throw new UsuarioJaExisteException("Usuário já existente");
        }

        Usuario usuario = modelMapper.map(credenciais, Usuario.class);
        usuario.setPassword(CriptografiaSenha.criptografia(usuario.getPassword()));
        Usuario salvo = usuarioRepository.save(usuario);

        return modelMapper.map(salvo, UsuarioDTO.class);
    }

    public boolean existeUsuario(String username) {
        return usuarioRepository.existsByUsername(username);
    }
}