package projetoconsenso.ProjetoConsenso.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import projetoconsenso.ProjetoConsenso.model.Login;
import projetoconsenso.ProjetoConsenso.model.Usuario;
import projetoconsenso.ProjetoConsenso.service.UsuarioService;


@RestController
@CrossOrigin
public class LoginController {
    
    @PostMapping("/login")
    public ResponseEntity<Object> fazerLogin(@RequestBody Login login){

        try{
            Usuario verificarEmail = usuarioService.findByemail(login.getEmail()).get();
            if(verificarEmail.getSenha().equals(login.getSenha())){
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(verificarEmail);
            }else{
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Login errado!");
                }
         
        }catch(RuntimeException erroLogin) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroLogin.getMessage());
        }
    }
    
@Autowired
UsuarioService usuarioService;

}
