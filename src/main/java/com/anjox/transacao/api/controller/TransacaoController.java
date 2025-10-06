package com.anjox.transacao.api.controller;

import com.anjox.transacao.api.dto.request.RequestTransacaoDto;
import com.anjox.transacao.api.services.TransacaoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private static final Logger log = LoggerFactory.getLogger(TransacaoController.class);
    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<?> createTransacao(@RequestBody @Valid RequestTransacaoDto dto){
        transacaoService.addTransacao(dto);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTransacao(){
        transacaoService.deleteTransacao();
        return ResponseEntity.ok().build();
    }
}
