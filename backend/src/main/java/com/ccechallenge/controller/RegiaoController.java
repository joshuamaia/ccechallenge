package com.ccechallenge.controller;

import com.ccechallenge.dto.Regiao;
import com.ccechallenge.service.RegiaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/regioes")
public class RegiaoController {

    private final RegiaoService regiaoService;

    public RegiaoController(RegiaoService regiaoService) {
        this.regiaoService = regiaoService;
    }

    @GetMapping("/findBySigla/{sigla}")
    public ResponseEntity<List<Regiao>> buscarRegiaoBySigla(@PathVariable String sigla){
        List<Regiao> regiao = this.regiaoService.buscarRegiaoBySigle(sigla);
        return new ResponseEntity<>(regiao, HttpStatus.OK);
    }
}
