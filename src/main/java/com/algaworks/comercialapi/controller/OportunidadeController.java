package com.algaworks.comercialapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.comercialapi.model.Oportunidade;
import com.algaworks.comercialapi.service.OportunidadeService;

// GET, POST ... -> http://localhost:8080/oportunidades

@CrossOrigin
@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {

    @Autowired
    private OportunidadeService oportunidadeService;

    @GetMapping
    public List<Oportunidade> listar(){
        return oportunidadeService.listarOportunidade();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oportunidade> buscar(@PathVariable Long id) {
        Oportunidade oportunidade = oportunidadeService.buscarOportunidade(id);
        return ResponseEntity.ok().body(oportunidade);
    }
  
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Oportunidade adicionar(@Validated @RequestBody Oportunidade oportunidade) {
        return oportunidadeService.adicionarOportunidade(oportunidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Oportunidade> atualizar(@PathVariable Long id, @Validated @RequestBody Oportunidade dadosOportunidade) {
        Oportunidade oportunidadeAtualizada = oportunidadeService.atualizarOportunidade(id, dadosOportunidade);
        return ResponseEntity.ok(oportunidadeAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        oportunidadeService.excluirOportunidade(id);
        return ResponseEntity.noContent().build();
    }
}
