package projetoconsenso.ProjetoConsenso.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projetoconsenso.ProjetoConsenso.model.Agendamento;

public interface AgendamentoService extends JpaRepository<Agendamento,Integer>{

    List<Agendamento>findAgendamentoByUsuarioIdUsuario(Integer id);
}