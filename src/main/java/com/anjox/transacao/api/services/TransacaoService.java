package com.anjox.transacao.api.services;

import com.anjox.transacao.api.dto.request.RequestTransacaoDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {

    private final List<RequestTransacaoDto> transacoes = new ArrayList<>();

    public void addTransacao(RequestTransacaoDto dto) {

        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            throw new RuntimeException("data e hora nao pode ser no futuro");
        }
        if (dto.valor().compareTo(BigDecimal.ZERO)< 0) {
            throw new RuntimeException("valor nao pode ser negativo");
        }
        transacoes.add(dto);
    }

    public void deleteTransacao() {
        transacoes.clear();
    }
}
