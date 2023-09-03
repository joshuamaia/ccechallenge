package com.ccechallenge.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccechallenge.dto.Agentes;
import com.ccechallenge.model.AgenteModel;
import com.ccechallenge.model.AgentesModel;
import com.ccechallenge.model.CompraModel;
import com.ccechallenge.model.GeracaoModel;
import com.ccechallenge.model.PrecoMedioModel;
import com.ccechallenge.model.RegiaoModel;
import com.ccechallenge.model.ValorModel;
import com.ccechallenge.service.AgentesService;
import com.ccechallenge.service.port.ProcessarDadosXmlAgentesPort;
import com.ccechallenge.service.port.SalvarAgentesPort;
import com.ccechallenge.utils.UtilityConvert;

@Service
public class AgentesServiceImpl implements AgentesService, UtilityConvert<AgentesModel, Agentes, Agentes> {

	private final SalvarAgentesPort<AgentesModel> salvarAgentesPort;

	private final ProcessarDadosXmlAgentesPort<InputStream, Agentes> processarDadosXmlAgentesPort;

	private final ModelMapper modelMapper;

	public AgentesServiceImpl(ProcessarDadosXmlAgentesPort<InputStream, Agentes> processarDadosXmlAgentesPort,
			SalvarAgentesPort<AgentesModel> salvarAgentesPort, ModelMapper modelMapper) {
		this.processarDadosXmlAgentesPort = processarDadosXmlAgentesPort;
		this.salvarAgentesPort = salvarAgentesPort;
		this.modelMapper = modelMapper;
	}

	@Override
	@Transactional
	public void processarDadosAgentes(InputStream file) {
		Agentes agentes = this.processarDadosXmlAgentesPort.execute(file);
		AgentesModel agentesModel = this.convertRequestForEntity(agentes);

		for (AgenteModel agenteModel : agentesModel.getAgente()) {

			agenteModel.setAgentesModel(agentesModel);

			for (RegiaoModel regiaoModel : agenteModel.getRegiao()) {
				updateRegiaoModel(regiaoModel, agenteModel);
				updateCompraModel(regiaoModel.getCompra(), regiaoModel);
				updatePrecoMedioModel(regiaoModel.getPrecoMedio(), regiaoModel);
				updateGeracaoModell(regiaoModel.getGeracao(), regiaoModel);
			}
		}
		this.salvarAgentesPort.execute(agentesModel);
	}

	private RegiaoModel updateRegiaoModel(RegiaoModel regiaoModel, AgenteModel agenteModel) {
		regiaoModel.setAgenteModel(agenteModel);

		if (regiaoModel.getCompra().getValores() == null) {
			regiaoModel.getCompra().setValores(new ArrayList<>());
		}
		if (regiaoModel.getGeracao().getValores() == null) {
			regiaoModel.getGeracao().setValores(new ArrayList<>());
		}
		if (regiaoModel.getPrecoMedio().getValores() == null) {
			regiaoModel.getPrecoMedio().setValores(new ArrayList<>());
		}
		return regiaoModel;
	}

	private CompraModel updateCompraModel(CompraModel compraModel, RegiaoModel regiaoModel) {
		compraModel.setRegiaoModel(regiaoModel);
		for (BigDecimal valor : compraModel.getValor()) {
			ValorModel valorModel = new ValorModel();
			valorModel.setValor(valor);
			compraModel.getValores().add(valorModel);
		}
		return compraModel;
	}

	private PrecoMedioModel updatePrecoMedioModel(PrecoMedioModel precoMedioModel, RegiaoModel regiaoModel) {
		precoMedioModel.setRegiaoModel(regiaoModel);
		for (BigDecimal valor : precoMedioModel.getValor()) {
			ValorModel valorModel = new ValorModel();
			valorModel.setValor(valor);
			precoMedioModel.getValores().add(valorModel);
		}
		return precoMedioModel;
	}

	private GeracaoModel updateGeracaoModell(GeracaoModel geracaoModel, RegiaoModel regiaoModel) {
		geracaoModel.setRegiaoModel(regiaoModel);
		for (BigDecimal valor : geracaoModel.getValor()) {
			ValorModel valorModel = new ValorModel();
			valorModel.setValor(valor);
			geracaoModel.getValores().add(valorModel);
		}
		return geracaoModel;
	}

	@Override
	public AgentesModel convertRequestForEntity(Agentes agentes) {
		return modelMapper.map(agentes, AgentesModel.class);
	}

	@Override
	public Agentes convertEntityForResponse(AgentesModel agentesModel) {
		return modelMapper.map(agentesModel, Agentes.class);
	}
}
