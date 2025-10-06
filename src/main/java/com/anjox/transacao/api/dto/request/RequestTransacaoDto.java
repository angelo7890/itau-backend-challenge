package com.anjox.transacao.api.dto.request;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record RequestTransacaoDto(BigDecimal valor, OffsetDateTime dataHora) {
}
