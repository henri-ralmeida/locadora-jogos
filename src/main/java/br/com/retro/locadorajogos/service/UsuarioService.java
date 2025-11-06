package br.com.retro.locadorajogos.service;

import br.com.retro.locadorajogos.domain.Usuario;
import br.com.retro.locadorajogos.dto.UsuarioDTO;
import br.com.retro.locadorajogos.exception.UsernameAlreadyExistsException;
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

    private final UsuarioRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public UsuarioDTO criarUsuario(UsuarioDTO credenciais) {
        // Verifica se o usuário já existe
        if (repository.existsByUsername(credenciais.getUsername())) {
            throw new UsernameAlreadyExistsException("Usuário já existente");
        }
        
        Usuario usuario = modelMapper.map(credenciais, Usuario.class);
        String senhaCriptografada = CriptografiaSenha.criptografia(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);

        Usuario usuarioSalvo = repository.save(usuario);
        
        return modelMapper.map(usuarioSalvo, UsuarioDTO.class);
    }
}
