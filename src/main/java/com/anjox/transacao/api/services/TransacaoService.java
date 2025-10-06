package com.anjox.transacao.api.services;

import com.anjox.transacao.api.dto.request.RequestTransacaoDto;
import com.anjox.transacao.api.dto.response.ResponseEstatisticasDto;
import com.anjox.transacao.api.exceptions.UnprocessableEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {

    private final List<RequestTransacaoDto> transacoes = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(TransacaoService.class);

    private static final long TRANSACTION_VALIDITY_SECONDS = 600L;

    public void addTransacao(RequestTransacaoDto dto) {

        log.info("iniciado o processamento da transacao: {}", dto.toString());

        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("data e hora da transacao esta invalida");
            throw new UnprocessableEntity("data e hora invalidos");
        }
        if (dto.valor() < 0) {
            log.error("valor da transacao deve ser maior que zero");
            throw new UnprocessableEntity("valor invalido");
        }

        log.info("finalizado o processamento da transacao: {}", dto.toString());
        transacoes.add(dto);
    }

    public void deleteTransacao() {
        log.info("limpando lista de transacoes");
        transacoes.clear();
    }

    public ResponseEstatisticasDto getEstatisticas() {

        log.info("buscando transacoes do ultimo minuto");

        OffsetDateTime dataHora = OffsetDateTime.now(ZoneOffset.UTC).minusSeconds(TRANSACTION_VALIDITY_SECONDS);

        var itens = transacoes.stream()
                .filter(t -> t.dataHora().isAfter(dataHora))
                .toList();

        var sumary = itens
                .stream()
                    .mapToDouble(RequestTransacaoDto::valor)
                        .summaryStatistics();

        log.info("retornando transacoes do ultimo minuto");
        return  new ResponseEstatisticasDto(sumary);
    }
}
