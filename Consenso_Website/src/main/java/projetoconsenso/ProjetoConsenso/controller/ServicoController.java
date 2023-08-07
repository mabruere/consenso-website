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

import projetoconsenso.ProjetoConsenso.model.Servico;
import projetoconsenso.ProjetoConsenso.service.ServicoService;


@RestController

public class ServicoController{

    @CrossOrigin
    @PostMapping("/servico")
    public ResponseEntity<Object> criarNovoServico(@RequestBody Servico servico){
        try{
        servicoService.save(servico); 
        return ResponseEntity.status(HttpStatus.CREATED).body(servico);
        }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");
        }
    }

    @CrossOrigin
    @GetMapping("/servico")
    public List<Servico> todosServicos(){
        return servicoService.findAll();
    }

    @GetMapping("/servico/{id}")
    public ResponseEntity<Object> servicoPorId(@PathVariable("id") Integer id){
        try{
        servicoService.findById(id).get();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Busca pelo id realizada");
        }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");
        }
    }

    @CrossOrigin
    @GetMapping("/servico/usuario/{id}")
    public List<Servico> servicoPorIdUsuario(@PathVariable("id") Integer id){
        
        return servicoService.findServicoByUsuarioIdUsuario(id);
       
        }
        

    @CrossOrigin
    @DeleteMapping("/servico/{id}")
    public ResponseEntity<Object> deletarServico(@PathVariable("id") Integer id){
        try{
        servicoService.deleteById(id);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deletado com sucesso");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @CrossOrigin
    @PutMapping("/servico")
    public ResponseEntity<Object> atualizarServico(@RequestBody Servico servico){
        try{
        Servico servicoBD = servicoService.findById(servico.getIdServico()).get();

        servicoBD.setNome(servico.getNome());
        servicoBD.setDescricao(servico.getDescricao());

        servicoBD = servicoService.save(servicoBD);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicoBD);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");
        }
    }

    @Autowired
    private ServicoService servicoService;

 

}
