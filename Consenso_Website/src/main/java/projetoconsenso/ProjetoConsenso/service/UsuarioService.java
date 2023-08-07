package projetoconsenso.ProjetoConsenso.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import projetoconsenso.ProjetoConsenso.model.Usuario;

public interface UsuarioService extends JpaRepository<Usuario,Integer>{
    
    Optional<Usuario>findById(Integer id);
    Optional<Usuario>findByemail( String email);
}
