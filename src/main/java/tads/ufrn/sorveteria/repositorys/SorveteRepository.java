package tads.ufrn.sorveteria.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import tads.ufrn.sorveteria.models.Sorvete;

import java.util.List;

public interface SorveteRepository extends JpaRepository<Sorvete, Long> {
    List<Sorvete> findByDeletedIsNull();

    Sorvete findByIdAndDeletedIsNull(Long id);
}