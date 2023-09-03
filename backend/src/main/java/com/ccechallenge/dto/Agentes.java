package com.ccechallenge.dto;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
public class Agentes {

	@JacksonXmlProperty(localName = "versao", isAttribute = true)
	private String versao;

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "agente")
	private List<Agente> agente;

}
