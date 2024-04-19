package com.algaworks.comercialapi.service;

import java.util.List;

import com.algaworks.comercialapi.model.Oportunidade;

// Este arquivo define as responsabilidades de cada função. "O que" elas vão fazer e não "Como" fazer.
public interface OportunidadeService {
    List<Oportunidade> listarOportunidade();
    Oportunidade buscarOportunidade(Long idOportunidade);
    Oportunidade adicionarOportunidade(Oportunidade oportunidade);
    Oportunidade atualizarOportunidade(Long id, Oportunidade oportunidade);
    void excluirOportunidade(Long id);
}
