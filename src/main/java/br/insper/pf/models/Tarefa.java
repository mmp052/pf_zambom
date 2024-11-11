package br.insper.pf.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "tarefas")
@Getter
@Setter
public class Tarefa {
    @Id
    private String id;
    private String titulo;
    private String descricao;
    private String status;
    private String projetoId;  // Relacionamento com o Projeto
    // getters e setters
}
