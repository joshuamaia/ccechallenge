package com.ccechallenge.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
public class Agente {
	
	@JacksonXmlProperty(localName = "codigo")
	private Long codigo;
	
	@JacksonXmlProperty(localName = "data")
	private LocalDateTime data;
	
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "regiao")
	private List<Regiao> regiao;

}
