package com.algaworks.comercialapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.comercialapi.exception.NotFoundException;
import com.algaworks.comercialapi.model.Oportunidade;
import com.algaworks.comercialapi.repository.OportunidadeRepository;
import com.algaworks.comercialapi.service.OportunidadeService;

@Service
public class OportunidadeServiceImpl implements OportunidadeService{

    @Autowired
    private OportunidadeRepository oportunidadeRepository;

    @Override
    public List<Oportunidade> listarOportunidade() {
        return oportunidadeRepository.findAll();
    }

    @Override
    public Oportunidade buscarOportunidade(Long idOportunidade) {
        Optional<Oportunidade> optionalOportunidade = oportunidadeRepository.findById(idOportunidade);
        if (optionalOportunidade.isEmpty()) {
            throw new NotFoundException("Oportunidade nao encontrada");
        }
        return optionalOportunidade.get();
        
        // De forma reduzida:
        // return oportunidadeRepository.findById(idOportunidade).orElseThrow(() -> new NotFoundException("Oportunidade nao encontrada"));
    }

    @Override
    public Oportunidade adicionarOportunidade(Oportunidade oportunidade) {
        // Primeiro, ele verifica se já existe uma oportunidade igual a que queremos adicionar.
        Optional<Oportunidade> oportunidadeExistente = oportunidadeRepository.findByDescricaoAndNomeProspecto(oportunidade.getDescricao(), oportunidade.getNomeProspecto());
        // Se já existir, ele nos avisa que não podemos adicionar porque já tem uma igual.
        if (oportunidadeExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma oportunidade igual");
        }

        oportunidade.setId(null);
        return oportunidadeRepository.save(oportunidade);
    }

    // private void atualizarOportunidade2(Long idOportunidade, Oportunidade oportunidade){

    //     Oportunidade oportunidadeDB = buscarOportunidade(idOportunidade);

    //     BeanUtils.copyProperties(oportunidade, oportunidadeDB, "id");

    //     oportunidadeRepository.save(oportunidadeDB);

    // }

    @Override
    public Oportunidade atualizarOportunidade(Long id, Oportunidade oportunidade) {

        // atualizarOportunidade2(id, oportunidade);
        return oportunidadeRepository.findById(id).map(oportunidadeExistente -> {
            oportunidadeExistente.setDescricao(oportunidade.getDescricao());
            oportunidadeExistente.setNomeProspecto(oportunidade.getNomeProspecto());
            oportunidadeExistente.setValor(oportunidade.getValor());
            return oportunidadeRepository.save(oportunidadeExistente);
        }).orElseThrow(() -> new NotFoundException("Oportunidade não encontrada para atualização"));
    }

    @Override
    public void excluirOportunidade(Long id) {
        //  Oportunidade oportunidade = buscarOportunidade(id);
        //  oportunidadeRepository.delete(oportunidade);
        
        Oportunidade oportunidade = oportunidadeRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Oportunidade não encontrada para exclusão"));
        oportunidadeRepository.delete(oportunidade);
    }
}
