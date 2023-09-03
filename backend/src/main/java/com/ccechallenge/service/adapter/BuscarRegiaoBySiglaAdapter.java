package com.ccechallenge.service.adapter;

import com.ccechallenge.model.RegiaoModel;
import com.ccechallenge.repository.RegiaoRepository;
import com.ccechallenge.service.port.BuscarRegiaoBySiglaPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscarRegiaoBySiglaAdapter implements BuscarRegiaoBySiglaPort<String, RegiaoModel> {

    private final RegiaoRepository regiaoRepository;

    public BuscarRegiaoBySiglaAdapter(RegiaoRepository regiaoRepository) {
        this.regiaoRepository = regiaoRepository;
    }

    @Override
    public List<RegiaoModel> execute(String sigla) {
        return this.regiaoRepository.findBySigla(sigla);
    }
}
