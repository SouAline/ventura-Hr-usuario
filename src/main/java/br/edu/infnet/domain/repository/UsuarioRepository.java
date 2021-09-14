package br.edu.infnet.domain.repository;

import br.edu.infnet.domain.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    Usuario findByEmail(String email);
}
