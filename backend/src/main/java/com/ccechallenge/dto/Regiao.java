package com.ccechallenge.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
public class Regiao {
	
	@JacksonXmlProperty(localName = "sigla", isAttribute = true)
	private String sigla;
	
    @JacksonXmlProperty(localName = "geracao")
    private Geracao geracao;
	
    @JacksonXmlProperty(localName = "compra")
    private Compra compra;

    @JacksonXmlProperty(localName = "precoMedio")
    private PrecoMedio precoMedio;

}
