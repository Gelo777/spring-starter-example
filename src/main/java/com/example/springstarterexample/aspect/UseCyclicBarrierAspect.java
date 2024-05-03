package com.example.springstarterexample.aspect;

import com.example.springstarterexample.annotation.UseCyclicBarrier;
import com.example.springstarterexample.annotation.UseSemaphore;
import com.example.springstarterexample.bean.BarrierAction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

@Aspect
@Slf4j
@RequiredArgsConstructor
public class UseCyclicBarrierAspect {

    private final Map<String, CyclicBarrier> barriers = new ConcurrentHashMap<>();

    private final BarrierAction barrierAction;

    @Pointcut("@annotation(useCyclicBarrier)")
    public void useCyclicBarrierPointcut(UseCyclicBarrier useCyclicBarrier) {
    }

    @Around("useCyclicBarrierPointcut(useCyclicBarrier)")
    public Object beforeUseCyclicBarrier(ProceedingJoinPoint joinPoint, UseCyclicBarrier useCyclicBarrier) throws Throwable {
        String barrierName = useCyclicBarrier.barrier();
        CyclicBarrier barrier = barriers.computeIfAbsent(barrierName, b -> new CyclicBarrier(useCyclicBarrier.parties(), barrierAction::action));
        try {
            barrier.await();
            log.info("Поток: {}, {}", Thread.currentThread().threadId(), " ждёт у барьера");
            return joinPoint.proceed();
        } finally {
            log.info("Поток: {}, {}", Thread.currentThread().threadId(), " пересёк барьер");
        }
    }
}
