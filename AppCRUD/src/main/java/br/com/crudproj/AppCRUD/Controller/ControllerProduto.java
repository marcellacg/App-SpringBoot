package br.com.crudproj.AppCRUD.Controller;

import br.com.crudproj.AppCRUD.Models.Produto;
import br.com.crudproj.AppCRUD.Models.Usuario;
import br.com.crudproj.AppCRUD.Repository.RepoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ControllerProduto {

    @Autowired
    private RepoProduto repoProduto;

    @GetMapping
    public List<Produto> getNome(){
        return repoProduto.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id, @RequestBody Produto produto){
        Optional<Produto> idProduto = repoProduto.findById(id);
        if (idProduto.isPresent()){
            return new ResponseEntity<Produto>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/save")
    public String salvarProduto(@RequestBody Produto produto){
        repoProduto.save(produto);
        return "Salvo!";
    }

    @PutMapping(value = "/update/{id}")
    public String updateProduto(@PathVariable long id, @RequestBody Produto produto){
        Produto updatedProduto = repoProduto.findById(id).get();
        updatedProduto.setNome(produto.getNome());
        updatedProduto.setPreco(produto.getPreco());
        repoProduto.save(updatedProduto);
        return "Atualizado!";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deletarProduto(@PathVariable long id){
        Produto deletedProduto = repoProduto.findById(id).get();
        repoProduto.delete(deletedProduto);
        return "Produto" + getNome() + "deletado!";
    }
}
