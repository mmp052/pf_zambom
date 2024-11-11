package br.insper.pf.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "projetos")
@Getter
@Setter
public class Projeto {
    @Id
    private String id;
    private String nome;
    private String descricao;
    // getters e setters
}