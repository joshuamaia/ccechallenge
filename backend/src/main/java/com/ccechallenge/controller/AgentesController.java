package com.ccechallenge.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ccechallenge.service.AgentesService;

@RestController
@RequestMapping("api/v1/agentes")
public class AgentesController {

	private final AgentesService agentesService;

	public AgentesController(AgentesService agentesService) {
		this.agentesService = agentesService;
	}

	@PostMapping("/upload-xml")
	public ResponseEntity uploadAndParseXML(@RequestParam("file") MultipartFile file) {
		try {
			this.agentesService.processarDadosAgentes(file.getInputStream());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
}
