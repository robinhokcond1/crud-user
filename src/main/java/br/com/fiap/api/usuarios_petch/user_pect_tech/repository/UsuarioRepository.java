package br.com.fiap.api.usuarios_petch.user_pect_tech.repository;

import br.com.fiap.api.usuarios_petch.user_pect_tech.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
