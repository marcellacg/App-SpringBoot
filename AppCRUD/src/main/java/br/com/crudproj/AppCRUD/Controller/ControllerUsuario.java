package br.com.crudproj.AppCRUD.Controller;

import br.com.crudproj.AppCRUD.Models.Usuario;
import br.com.crudproj.AppCRUD.Repository.RepoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/usuarios")
public class ControllerUsuario {

    @Autowired
    private RepoUsuario repoUsuario;


    @GetMapping
    public List<Usuario> getUsuarios(){
        return repoUsuario.findAll();
    }


    @PostMapping(value = "/save")
    public String salvarUsuario(@RequestBody Usuario usuario){
        repoUsuario.save(usuario);
        return "Salvo!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id, @RequestBody Usuario usuario){
        Optional<Usuario> idUsuario = repoUsuario.findById(id);
        if (idUsuario.isPresent()){
            return new ResponseEntity<Usuario>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/update/{id}")
    public String updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario updatedUsuario = repoUsuario.findById(id).get();
        updatedUsuario.setLogin(usuario.getLogin());
        updatedUsuario.setSenha(usuario.getSenha());
        repoUsuario.save(updatedUsuario);
        return "Atualizado!";
    }

    @DeleteMapping(value = "delete/{id}")
    public String deletarUsuario(@PathVariable Long id){
        Usuario deletedUsuario = repoUsuario.findById(id).get();
        repoUsuario.delete(deletedUsuario);
        return "Usuario " + id + " deletado!";
    }
}
