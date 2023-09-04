package com.ccechallenge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccechallenge.dto.ConsolidadoRegiaoDto;
import com.ccechallenge.dto.Regiao;
import com.ccechallenge.service.RegiaoService;

@RestController
@RequestMapping("api/v1/regioes")
public class RegiaoController {

	private final RegiaoService regiaoService;

	public RegiaoController(RegiaoService regiaoService) {
		this.regiaoService = regiaoService;
	}

	@GetMapping("/consolidado")
	public ResponseEntity<List<ConsolidadoRegiaoDto>> consolidadoRegiao() {
		List<ConsolidadoRegiaoDto> consolidado = this.regiaoService.consolidadoRegiao();
		return new ResponseEntity<>(consolidado, HttpStatus.OK);
	}

	@GetMapping("/findBySigla/{sigla}")
	public ResponseEntity<List<Regiao>> buscarRegiaoBySigla(@PathVariable String sigla) {
		List<Regiao> regiao = this.regiaoService.buscarRegiaoBySigle(sigla);
		return new ResponseEntity<>(regiao, HttpStatus.OK);
	}
}
