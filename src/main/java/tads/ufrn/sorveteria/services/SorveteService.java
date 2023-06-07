package tads.ufrn.sorveteria.services;

import org.springframework.stereotype.Service;
import tads.ufrn.sorveteria.models.Sorvete;
import tads.ufrn.sorveteria.repositorys.SorveteRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SorveteService {
    private final SorveteRepository repository;

    public SorveteService(SorveteRepository sorveteRepository) {
        this.repository = sorveteRepository;
    }

    public void salvar(Sorvete s) {
        repository.save(s);
    }

    public List<Sorvete> listar() {
        return repository.findByDeletedIsNull();
    }

    public Optional<Sorvete> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        Sorvete sorvete = repository.findByIdAndDeletedIsNull(id);
        if(sorvete != null){
            sorvete.setDeleted(Date.valueOf(LocalDate.now()));
            repository.save(sorvete);
        }

    }

    public Sorvete editar(Sorvete f){
        return repository.saveAndFlush(f);
    }
}