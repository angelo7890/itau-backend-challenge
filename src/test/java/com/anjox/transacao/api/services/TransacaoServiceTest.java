package com.anjox.transacao.api.services;

import com.anjox.transacao.api.dto.request.RequestTransacaoDto;
import com.anjox.transacao.api.dto.response.ResponseEstatisticasDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
    @InjectMocks
    private TransacaoService service;
    ResponseEstatisticasDto response;
    List<RequestTransacaoDto> transacoes;
    @BeforeEach
    public void setUp() {
        transacoes = List.of(
                new RequestTransacaoDto(10.5, OffsetDateTime.now()),
                new RequestTransacaoDto(20.0, OffsetDateTime.now()),
                new RequestTransacaoDto(5.0, OffsetDateTime.now())
        );
        DoubleSummaryStatistics stats = transacoes.stream()
                .mapToDouble(RequestTransacaoDto::valor)
                .summaryStatistics();

        response = new ResponseEstatisticasDto(stats);
    }

    @Test
    public void calcularEstatisticasComSucesso(){
        when(service.getEstatisticas()).thenReturn(response);
        ResponseEstatisticasDto result = service.getEstatisticas();
        assertNotNull(result);
        assertEquals(3, result.getCount());
        assertEquals(35.5, result.getSum(), 0.0001);
        assertEquals(5.0, result.getMin(), 0.0001);
        assertEquals(20.0, result.getMax(), 0.0001);
        assertEquals(11.833333333333334, result.getAvg(), 0.0001);
    }

    @Test
    public void calcularEstatisticasQuandoListaVazia() {
        DoubleSummaryStatistics stats = new DoubleSummaryStatistics();
        ResponseEstatisticasDto esperado = new ResponseEstatisticasDto(stats);
        when(service.getEstatisticas()).thenReturn(esperado);
        ResponseEstatisticasDto result = service.getEstatisticas();
        verify(service, times(1)).getEstatisticas();

        assertThat(result.getCount()).isEqualTo(0);
        assertThat(result.getSum()).isEqualTo(0.0);
        assertThat(result.getMin()).isEqualTo(Double.POSITIVE_INFINITY);
        assertThat(result.getMax()).isEqualTo(Double.NEGATIVE_INFINITY);
        assertThat(result.getAvg()).isEqualTo(0.0);
    }

}
