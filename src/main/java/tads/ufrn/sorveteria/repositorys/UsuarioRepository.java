package tads.ufrn.sorveteria.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import tads.ufrn.sorveteria.models.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findUsuarioByLogin(String login);

}