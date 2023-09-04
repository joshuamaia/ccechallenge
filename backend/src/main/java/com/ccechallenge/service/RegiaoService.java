package com.ccechallenge.service;

import com.ccechallenge.dto.ConsolidadoRegiaoDto;
import com.ccechallenge.dto.Regiao;

import java.util.List;

public interface RegiaoService {

    List<Regiao> buscarRegiaoBySigle(String sigla);
    
    public List<ConsolidadoRegiaoDto> consolidadoRegiao();

}
