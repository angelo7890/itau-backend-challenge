package com.anjox.transacao.api.controller;

import com.anjox.transacao.api.dto.request.RequestTransacaoDto;
import com.anjox.transacao.api.dto.response.ResponseEstatisticasDto;
import com.anjox.transacao.api.services.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "transacao", description = "cria e deleta transacoes")
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private static final Logger log = LoggerFactory.getLogger(TransacaoController.class);
    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @Operation(
            summary = "cria uma nova transação",
            description = "recebe um DTO com valor e dataHora para criar uma transação.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "transação criada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RequestTransacaoDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "422", description = "dados inválidos fornecidos"),
                    @ApiResponse(responseCode = "500", description = "erro interno no servidor")
            }
    )
    @PostMapping
    public ResponseEntity<?> createTransacao(@RequestBody @Valid RequestTransacaoDto dto){
        log.info("transaco recebida: {}", dto.toString());
        transacaoService.addTransacao(dto);
        return ResponseEntity.created(null).build();
    }

    @Operation(summary = "deleta as transacoes"
            ,description = "contem a operacao para deletar as transacoes",
            responses = @ApiResponse(
                    responseCode = "200"))
    @DeleteMapping
    public ResponseEntity<?> deleteTransacao(){
        transacaoService.deleteTransacao();
        return ResponseEntity.ok().build();
    }
}
