package com.ccechallenge.dto;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class Teste {
    public static void main(String[] args) {

        try {
            // Crie um objeto ObjectMapper do Jackson para XML
            JavaTimeModule module = new JavaTimeModule();
            LocalDateTimeDeserializer localDateTimeDeserializer =  new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME);
            module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
            ObjectMapper objectMapper = new XmlMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);;
            objectMapper.registerModule(module);

            // Desserializar o arquivo XML
            Agentes agentes = objectMapper.readValue(new File("/home/breno/Downloads/exemplo_01.xml"), Agentes.class);

            for (Agente agente : agentes.getAgente()) {

                System.out.println("Código: " + agente.getCodigo());
                System.out.println("Data: " + agente.getData());

                for (Regiao regiao : agente.getRegiao()) {
                    System.out.println("Região Sigla: " + regiao.getSigla());

                    // Agora você tem o objeto Java populado com os valores do XML
                    Compra compra = regiao.getCompra();
                    for (BigDecimal valor : compra.getValor()) {
                        System.out.println(valor);
                    }
                    PrecoMedio precoMedio = regiao.getPrecoMedio();
                    for (BigDecimal valor : precoMedio.getValor()) {
                        System.out.println(valor);
                    }
                    Geracao geracao = regiao.getGeracao();
                    for (BigDecimal valor : geracao.getValor()) {
                        System.out.println(valor);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
