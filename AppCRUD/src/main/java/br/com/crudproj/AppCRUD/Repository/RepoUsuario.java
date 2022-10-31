package br.com.crudproj.AppCRUD.Repository;

import br.com.crudproj.AppCRUD.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoUsuario extends JpaRepository<Usuario, Long> {

}
