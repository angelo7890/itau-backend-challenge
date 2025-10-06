package com.anjox.transacao.api.controller;

import com.anjox.transacao.api.services.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

    private final TransacaoService transacaoService;

    public EstatisticasController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public ResponseEntity<?> getEstatisticas(){
        return ResponseEntity.ok(transacaoService.getEstatisticas());
    }
}
