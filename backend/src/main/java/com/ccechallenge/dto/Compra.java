package com.ccechallenge.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
public class Compra {

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "valor")
	private List<BigDecimal> valor;

}
