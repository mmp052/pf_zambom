package br.insper.pf.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.insper.pf.models.Tarefa;


public interface TarefaRepository extends MongoRepository<Tarefa, String> {
    
}
