package com.example.springstarterexample.aspect;

import com.example.springstarterexample.annotation.UseSemaphore;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

@Aspect
@Slf4j
public class UseSemaphoreAspect {

    private final Map<String, Semaphore> semaphores = new ConcurrentHashMap<>();

    @Pointcut("@annotation(useSemaphore)")
    public void useSemaphoreAspect(UseSemaphore useSemaphore) {
    }

    @Around("useSemaphoreAspect(useSemaphore)")
    public Object useSemaphoreAround(ProceedingJoinPoint joinPoint, UseSemaphore useSemaphore) throws Throwable {
        Semaphore semaphore = semaphores.computeIfAbsent(useSemaphore.target(), s -> new Semaphore(useSemaphore.permits()));
        try {
            semaphore.acquire();
            log.info("Семафор захвачен потоком: {}", Thread.currentThread().threadId());
            return joinPoint.proceed();
        } finally {
            semaphore.release();
            log.info("Семафор отпущен потоком: {}", Thread.currentThread().threadId());
        }
    }
}
