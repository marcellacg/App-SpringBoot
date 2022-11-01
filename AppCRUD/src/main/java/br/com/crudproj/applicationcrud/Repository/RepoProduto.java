package br.com.crudproj.applicationcrud.Repository;

import br.com.crudproj.applicationcrud.Models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoProduto extends JpaRepository<Produto, Long> {

}

