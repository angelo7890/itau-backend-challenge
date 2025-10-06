package com.anjox.transacao.api.services;

import com.anjox.transacao.api.dto.request.RequestTransacaoDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {

    private final List<RequestTransacaoDto> transacoes = new ArrayList<>();
}
