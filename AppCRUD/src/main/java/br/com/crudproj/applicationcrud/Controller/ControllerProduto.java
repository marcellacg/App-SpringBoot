package br.com.crudproj.applicationcrud.Controller;

import br.com.crudproj.applicationcrud.Models.Produto;
import br.com.crudproj.applicationcrud.Repository.RepoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping(value = "/save")
    public String salvarProduto(@RequestBody Produto produto){
        repoProduto.save(produto);
        return "Salvo!";
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

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
        Optional<Produto> updatedProduto = repoProduto.findById(id);
        if (updatedProduto.isPresent()) {
            Produto prd = updatedProduto.get();
            prd.setNome(produto.getNome());
            prd.setPreco(produto.getPreco());
            prd.setQnt(produto.getQnt());
            repoProduto.save(prd);
            return new ResponseEntity<Produto>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<Object> deletarProduto(@PathVariable Long id) {
        Optional<Produto> deletedProduto = repoProduto.findById(id);
        if (deletedProduto.isPresent()) {
            repoProduto.delete(deletedProduto.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
