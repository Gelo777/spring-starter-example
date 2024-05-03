package com.example.springstarterexample.init;

import com.example.springstarterexample.bean.ThreadsInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class ConcurrencyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("Вызов ConcurrencyApplicationContextInitializer");
        applicationContext.getBeanFactory().registerSingleton(ThreadsInfo.class.getSimpleName(), new ThreadsInfo());
    }
}
