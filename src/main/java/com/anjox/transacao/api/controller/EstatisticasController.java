package com.anjox.transacao.api.controller;

import com.anjox.transacao.api.dto.response.ResponseEstatisticasDto;
import com.anjox.transacao.api.services.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "estatisticas", description = "retorna as estatatisticas das transacoes")
@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

    private final TransacaoService transacaoService;

    public EstatisticasController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @Operation(summary = "retorna as estatisticas"
            ,description = "contem a operacao para retornar as estatisticas",
            responses = @ApiResponse(
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEstatisticasDto.class ))))
    @GetMapping
    public ResponseEntity<?> getEstatisticas(){
        return ResponseEntity.ok(transacaoService.getEstatisticas());
    }
}
