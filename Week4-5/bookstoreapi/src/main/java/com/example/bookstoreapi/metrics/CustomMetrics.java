package com.example.bookstoreapi.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics {

    private final Counter bookViewCounter;
    private final Gauge bookGauge;

    public CustomMetrics(MeterRegistry meterRegistry) {
        this.bookViewCounter = meterRegistry.counter("book.views");
        this.bookGauge = Gauge.builder("book.gauge", this, CustomMetrics::getBookCount)
                               .register(meterRegistry);
    }

    public void incrementBookViewCounter() {
        bookViewCounter.increment();
    }

    private double getBookCount() {
        return 0.0;
    }

    public void setBookGauge(double value) {
    }
}
