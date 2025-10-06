package com.anjox.transacao.api.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record RequestTransacaoDto(
        @NotNull
        Double valor,
        @NotNull
        OffsetDateTime dataHora
) {
}
