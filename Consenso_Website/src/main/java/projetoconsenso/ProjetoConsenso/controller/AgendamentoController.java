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

import projetoconsenso.ProjetoConsenso.model.Agendamento;
import projetoconsenso.ProjetoConsenso.service.AgendamentoService;

@RestController
public class AgendamentoController{

    
    @CrossOrigin
    @PostMapping("/agendamento")
    public ResponseEntity<Object> novoAgendamento(@RequestBody Agendamento agendar){
       try{
        agendamentoService.save(agendar);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendar);
       }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");
       }
    }

    @GetMapping("/agendamento")
    public List<Agendamento> agendamentoCadastrados(){
        return agendamentoService.findAll();
    }

    @GetMapping("/agendamento/{id}")
    public ResponseEntity<Object> agendamentoUnico(@PathVariable("id") Integer id){
        try{

        agendamentoService.findById(id).get();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Busca pelo id realizada");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");
        }    }
    
    @CrossOrigin
    @GetMapping("/agendamento/usuario/{id}")
    public List<Agendamento> agendamentoPorIdUsuario(@PathVariable("id") Integer id){
            return agendamentoService.findAgendamentoByUsuarioIdUsuario(id);
           
            }    
    
    @CrossOrigin
    @DeleteMapping("/agendamento/{id}")
    public ResponseEntity<Object> deletarAgendamento(@PathVariable("id") Integer id){
        try{

        agendamentoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Agendamento deletado");
        } catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");
        }
    }

    
    @CrossOrigin
    @PutMapping("/agendamento")
    public ResponseEntity<Object> atualizarAgendamento(@RequestBody Agendamento agendar){
        try{
        
         return ResponseEntity.status(HttpStatus.ACCEPTED).body(agendamentoService.save(agendar));
        } catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");
        }

     }
     @Autowired
     private AgendamentoService agendamentoService;
 
}


    
