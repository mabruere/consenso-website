package projetoconsenso.ProjetoConsenso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import projetoconsenso.ProjetoConsenso.model.Usuario;
import projetoconsenso.ProjetoConsenso.service.UsuarioService;

@RestController
@CrossOrigin
public class UsuarioController {

    @PostMapping("/usuario")
    public ResponseEntity<Object> criarNovoUsuario(@RequestBody Usuario usuario) {

        try {
            if (usuario.getTipousuario().getIdTipoUsuario() == 1
                    || usuario.getTipousuario().getIdTipoUsuario() == 2) {
                usuarioService.save(usuario);
                return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usuario.getTipousuario().getNome());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());// string
        }

    }
    

    
    @GetMapping("/usuario")
    public List<Usuario> usuariosRegistrados(){
        return usuarioService.findAll();
    }
    
    @GetMapping("/usuario/{id}")
    public Usuario usuarioRegistradoPorId(@PathVariable("id") Integer id){
        return usuarioService.findById(id).get();
    }
    
    @DeleteMapping("/usuario/{id}")
    public String deletarUsuario(@PathVariable("id") Integer id){
        usuarioService.deleteById(id);
    
        return "Usuario deletado com sucesso!!";
    }
    
    @PutMapping("/usuario")
    public Usuario atualizarUsuario(@RequestBody Usuario usuario){
        Usuario usuarioBD = usuarioService.findById(usuario.getIdUsuario()).get();
    
        usuarioBD.setNome(usuario.getNome()); 
        usuarioBD.setSobrenome(usuario.getSobrenome());
        usuarioBD.setEmail(usuario.getEmail());
        usuarioBD.setSenha(usuario.getSenha());
    
        usuarioBD = usuarioService.save(usuarioBD);
    
        return usuarioBD; 
    
    }
    
    
    
    @Autowired
    private UsuarioService usuarioService;
    
}
    