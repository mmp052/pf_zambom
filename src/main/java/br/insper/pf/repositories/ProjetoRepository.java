package br.insper.pf.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.insper.pf.models.Projeto;

public interface ProjetoRepository extends MongoRepository<Projeto, String> {
}