package com.ccechallenge.service.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.ccechallenge.dto.Agente;
import com.ccechallenge.dto.Agentes;
import com.ccechallenge.service.port.ProcessarDadosXmlAgentesPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProcessarDadosXmlAgentesAdapter implements ProcessarDadosXmlAgentesPort<InputStream, Agentes> {

	@Override
	public Agentes execute(InputStream file) {
		try {
			// Desserializar o arquivo XML
			JavaTimeModule module = new JavaTimeModule();
			LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(
					DateTimeFormatter.ISO_DATE_TIME);
			module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
			ObjectMapper objectMapper = new XmlMapper();
			objectMapper.registerModule(module);
			Agentes agentes = objectMapper.readValue(file, Agentes.class);
			this.gerarLogs(agentes);
			return agentes;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Algum error ocorreu ao processar o arquivo xml {} " + e.getMessage());
		}
	}

	private void gerarLogs(Agentes agentes) {
		for (Agente agente : agentes.getAgente()) {
			log.info("Código: " + agente.getCodigo());
		}
	}

}
