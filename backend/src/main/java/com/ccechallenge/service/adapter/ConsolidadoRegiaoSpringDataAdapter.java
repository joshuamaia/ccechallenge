package com.ccechallenge.service.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ccechallenge.dto.ConsolidadoRegiaoDto;
import com.ccechallenge.repository.RegiaoRepository;
import com.ccechallenge.service.port.ConsolidadoRegiaoPort;

@Component
public class ConsolidadoRegiaoSpringDataAdapter implements ConsolidadoRegiaoPort<ConsolidadoRegiaoDto> {

	private final RegiaoRepository regiaoRepository;

	public ConsolidadoRegiaoSpringDataAdapter(RegiaoRepository regiaoRepository) {
		this.regiaoRepository = regiaoRepository;
	}

	@Override
	public List<ConsolidadoRegiaoDto> execute() {
		return regiaoRepository.consolidadoRegiao();
	}

}
