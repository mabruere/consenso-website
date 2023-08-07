package projetoconsenso.ProjetoConsenso.service;

import org.springframework.data.jpa.repository.JpaRepository;

import projetoconsenso.ProjetoConsenso.model.TipoUsuario;

public interface TipoUsuarioService extends JpaRepository<TipoUsuario,Integer>{
}
