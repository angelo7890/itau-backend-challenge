package com.anjox.transacao.api.dto.response;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;

public class ResponseEstatisticasDto {

    private Long count;

    private BigDecimal sum;

    private BigDecimal avg;

    private BigDecimal min;

    private BigDecimal max;


    public ResponseEstatisticasDto(DoubleSummaryStatistics statistics) {
        if (statistics == null) {
            this.count = 0L;
            this.sum = BigDecimal.ZERO;
            this.avg = BigDecimal.ZERO;
            this.min = BigDecimal.ZERO;
            this.max = BigDecimal.ZERO;
            return;
        }
        this.count = statistics.getCount();
        this.sum = BigDecimal.valueOf(statistics.getSum());
        this.avg = BigDecimal.valueOf(statistics.getAverage());
        this.min = BigDecimal.valueOf(statistics.getMin());
        this.max = BigDecimal.valueOf(statistics.getMax());
    }
}
