package br.com.crudproj.applicationcrud.Repository;

import br.com.crudproj.applicationcrud.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoUsuario extends JpaRepository<Usuario, Long> {

}
