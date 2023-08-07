package projetoconsenso.ProjetoConsenso.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import projetoconsenso.ProjetoConsenso.model.TipoUsuario;
import projetoconsenso.ProjetoConsenso.service.TipoUsuarioService;


@RestController
public class TipoUsuarioController {
    @PostMapping("/tipousuario")
    public ResponseEntity<Object> adicionarTipoUsuario(@RequestBody TipoUsuario tipousuario){
        try{
        tipoUsuarioService.save(tipousuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipousuario);
        }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");
        }
    }
    

    @GetMapping("/tipousuario")
    public List<TipoUsuario> todosOsTiposUsuarios(){
        return tipoUsuarioService.findAll();
        
    }

    @GetMapping("/tipousuario/{id}")
    public ResponseEntity<Object> idTipoUsuario(@PathVariable("id") Integer id){
        try{
            tipoUsuarioService.findById(id).get();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Busca pelo id realizada");
            }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");
            }
        }
    

    @DeleteMapping("/tipousuario/{id}")
    public ResponseEntity<Object> deletaridUsuario(@PathVariable("id") Integer id){
        try{
        tipoUsuarioService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Tipo de usuario deletado");
            }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");
            }
        }

    
    @PutMapping("/tipousuario")
    public ResponseEntity<Object> atualizarTipoUsuario(@RequestBody TipoUsuario tipoUsuario){
        try{
        TipoUsuario tipoUsuarioBD = tipoUsuarioService.findById(tipoUsuario.getIdTipoUsuario()).get();

        tipoUsuarioBD.setNome(tipoUsuario.getNome());

        tipoUsuarioBD = tipoUsuarioService.save(tipoUsuarioBD);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoUsuarioBD);
        }catch(Exception e){
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");
    }
}
        

    @Autowired
    private TipoUsuarioService tipoUsuarioService;
    
}
