package com.example.springstarterexample.bean;

import com.example.springstarterexample.annotation.Lockable;
import com.example.springstarterexample.annotation.UseCyclicBarrier;
import com.example.springstarterexample.annotation.UseSemaphore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExampleBean {

    @Lockable(resource = "lockWith")
    public void lockWith() {
        log.info("Я вызываюсь из метода lockWith");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @UseCyclicBarrier(parties = 4, barrier = "barrier")
    public void barrier() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("barrier -> Я завершил работу " + Thread.currentThread().threadId());
    }

    @UseSemaphore(target = "semaphore", permits = 1)
    public void semaphore() {
        log.info("semaphore -> Я начинаю работу " + Thread.currentThread().threadId());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("semaphore -> Я завершил работу " + Thread.currentThread().threadId());
    }
}
