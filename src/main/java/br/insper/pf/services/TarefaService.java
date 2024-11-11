package br.insper.pf.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.insper.pf.models.Tarefa;
import br.insper.pf.repositories.TarefaRepository;

@Service
public class TarefaService {
    TarefaRepository tarefaRepository;

    public Tarefa criarTarefa(Tarefa tarefa) {
        tarefaRepository.save(tarefa);
        return tarefa;
    }

    public void deletarTarefa(String id) {
        tarefaRepository.deleteById(id);
    }

    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public Tarefa obterTarefa(String id) {
        return tarefaRepository.findById(id).orElse(null);
    }
}
