package com.anjox.transacao.api.services;

import com.anjox.transacao.api.dto.request.RequestTransacaoDto;
import com.anjox.transacao.api.exceptions.UnprocessableEntity;
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
            throw new UnprocessableEntity("data e hora invalidos");
        }
        if (dto.valor().compareTo(BigDecimal.ZERO)< 0) {
            throw new UnprocessableEntity("valor invalido");
        }
        transacoes.add(dto);
    }

    public void deleteTransacao() {
        transacoes.clear();
    }
}
