package com.example.springstarterexample.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "concurrency")
public class ConcurrencyProperties {
    private Boolean enabled;
    private Boolean lockEnabled;
    private Boolean cyclicBarrierEnabled;
    private Boolean semaphoreEnabled;
}
