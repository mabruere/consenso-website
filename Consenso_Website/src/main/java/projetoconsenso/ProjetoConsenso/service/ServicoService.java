package projetoconsenso.ProjetoConsenso.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import projetoconsenso.ProjetoConsenso.model.Servico;

public interface ServicoService extends JpaRepository<Servico,Integer>{

    List<Servico>findServicoByUsuarioIdUsuario(Integer id);

}