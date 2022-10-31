package br.com.crudproj.AppCRUD.Repository;

import br.com.crudproj.AppCRUD.Models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoProduto extends JpaRepository<Produto, Long> {

}

