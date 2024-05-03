package com.example.springstarterexample.config;

import com.example.springstarterexample.annotation.UseCyclicBarrier;
import com.example.springstarterexample.aspect.LockAspect;
import com.example.springstarterexample.aspect.UseCyclicBarrierAspect;
import com.example.springstarterexample.aspect.UseSemaphoreAspect;
import com.example.springstarterexample.bean.BarrierAction;
import com.example.springstarterexample.filter.ConditionalOnCyclicBarrierCondition;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

@AutoConfiguration
@EnableConfigurationProperties(ConcurrencyProperties.class)
@ConditionalOnProperty(prefix = "concurrency", value = "enabled", havingValue = "true", matchIfMissing = false)
public class ConcurrencyAutoConfiguration {

    @Bean
    @ConditionalOnExpression("${concurrency.lock-enabled:false}")
    public LockAspect lockAspect() {
        return new LockAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    public BarrierAction barrierAction() {
        return new BarrierAction();
    }

    @Bean
    @ConditionalOnProperty(prefix = "concurrency", value = "semaphore-enabled", havingValue = "true", matchIfMissing = true)
    public UseSemaphoreAspect useSemaphoreAspect() {
        return new UseSemaphoreAspect();
    }

    @Bean
    @ConditionalOnCyclicBarrierCondition
    public UseCyclicBarrierAspect useCyclicBarrierAspect(BarrierAction barrierAction) {
        return new UseCyclicBarrierAspect(barrierAction);
    }
}
