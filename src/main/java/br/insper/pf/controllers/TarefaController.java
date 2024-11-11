package br.insper.pf.controllers;

import org.springframework.http.HttpHeaders;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.bind.annotation.*;

import br.insper.pf.models.Tarefa;
import br.insper.pf.services.TarefaService;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa, @RequestHeader("Authorization") String token) {
        String papel = getPapel(token);
        if (!"ADMIN".equals(papel)) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(tarefaService.criarTarefa(tarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable String id,@RequestHeader("Authorization") String token) {
        String papel = getPapel(token);
        if (!"ADMIN".equals(papel)) {
            return ResponseEntity.status(403).build();
        }
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas(@RequestHeader("Authorization") String token) {
        String papel = getPapel(token);
        if (!"ADMIN".equals(papel) && !"DEVELOPER".equals(papel)) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(tarefaService.listarTarefas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> obterTarefa(@PathVariable String id,@RequestHeader("Authorization") String token) {
        String papel = getPapel(token);
        if (!"ADMIN".equals(papel) && !"DEVELOPER".equals(papel)) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(tarefaService.obterTarefa(id));
    }

    private String getPapel(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://184.72.80.215/usuario/validate";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token); // Ajuste se necessário para incluir "Bearer "
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            Map<String, String> responseBody = response.getBody();
            return responseBody != null ? responseBody.get("papel") : null;
        } catch (Exception e) {
            System.err.println("Erro ao validar token: " + e.getMessage());
            return null;
        }
    }
}

