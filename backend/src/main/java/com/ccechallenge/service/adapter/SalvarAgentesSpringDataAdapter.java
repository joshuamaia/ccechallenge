package com.ccechallenge.service.adapter;

import org.springframework.stereotype.Component;

import com.ccechallenge.model.AgentesModel;
import com.ccechallenge.repository.AgentesRepository;
import com.ccechallenge.service.port.SalvarAgentesPort;

@Component
public class SalvarAgentesSpringDataAdapter implements SalvarAgentesPort<AgentesModel> {

	private final AgentesRepository repository;
	
	public SalvarAgentesSpringDataAdapter(AgentesRepository repository) {
		this.repository = repository;
	}

	@Override
	public void execute(AgentesModel agentesModel) {
		this.repository.save(agentesModel);
	}

}
