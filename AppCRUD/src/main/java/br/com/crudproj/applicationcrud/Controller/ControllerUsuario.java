package br.com.crudproj.applicationcrud.Controller;

import br.com.crudproj.applicationcrud.Models.Usuario;
import br.com.crudproj.applicationcrud.Repository.RepoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class ControllerUsuario {

    @Autowired
    private RepoUsuario repoUsuario;


    @GetMapping
    public List<Usuario> getUsuarios() {
        return repoUsuario.findAll();
    }


    @PostMapping(value = "/save")
    public String salvarUsuario(@RequestBody Usuario usuario) {
        repoUsuario.save(usuario);
        return "Salvo!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> idUsuario = repoUsuario.findById(id);
        if (idUsuario.isPresent()) {
            return new ResponseEntity<Usuario>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> updatedUsuario = repoUsuario.findById(id);
        if (updatedUsuario.isPresent()) {
            Usuario usr = updatedUsuario.get();
            usr.setLogin(usuario.getLogin());
            usr.setSenha(usuario.getSenha());
            repoUsuario.save(usr);
            return new ResponseEntity<Usuario>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable Long id) {
        Optional<Usuario> deletedUsuario = repoUsuario.findById(id);
        if (deletedUsuario.isPresent()) {
            repoUsuario.delete(deletedUsuario.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}