package br.com.fiap.api.usuarios_petch.user_pect_tech.service;

import br.com.fiap.api.usuarios_petch.user_pect_tech.dto.UsuarioDTO;
import br.com.fiap.api.usuarios_petch.user_pect_tech.entities.Usuario;
import br.com.fiap.api.usuarios_petch.user_pect_tech.exception.ControllerNotFoundException;
import br.com.fiap.api.usuarios_petch.user_pect_tech.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Page<UsuarioDTO> findAll(Pageable pageable){
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return usuarios.map(this::toDTO);
    }
    public UsuarioDTO findById(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ControllerNotFoundException("Usuário não encontrado"));
        return toDTO(usuario);
    }
    public UsuarioDTO save(UsuarioDTO usuarioDto) {
        Usuario usuario = toEntity(usuarioDto);
        usuario = usuarioRepository.save(usuario);
        return toDTO(usuario);
    }
    public  UsuarioDTO update(Long id, UsuarioDTO usuarioDto){
        try {
            Usuario usuario = usuarioRepository.getReferenceById(id);
            usuario.setNome(usuarioDto.nome());
            usuario.setEmail(usuarioDto.email());
            usuario.setCpf(usuarioDto.cpf());
            usuario.setDataNascimento(usuarioDto.dataNascimento());

            usuario =usuarioRepository.save(usuario);
            return toDTO(usuario);

        }catch (EntityNotFoundException e){
            throw  new ControllerNotFoundException("Usuario não encontrado");
        }
    }
    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }
    private UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getDataNascimento()
        );
    }
    private Usuario toEntity(UsuarioDTO usuarioDto) {
        return new Usuario(
                usuarioDto.id(),
                usuarioDto.nome(),
                usuarioDto.email(),
                usuarioDto.cpf(),
                usuarioDto.dataNascimento()
        );
    }

}
