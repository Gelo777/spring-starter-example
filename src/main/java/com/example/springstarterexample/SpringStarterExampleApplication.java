package com.example.springstarterexample;

import com.example.springstarterexample.bean.ExampleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringStarterExampleApplication {

    @Autowired
    private ExampleBean exampleBean;

    public static void main(String[] args) {
        SpringApplication.run(SpringStarterExampleApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onEvent(){
//        new Thread(() -> exampleBean.lockWith()).start();
//        new Thread(() -> exampleBean.lockWith()).start();
//        new Thread(() -> exampleBean.lockWith()).start();

        new Thread(() -> exampleBean.barrier()).start();
        new Thread(() -> exampleBean.barrier()).start();
        new Thread(() -> exampleBean.barrier()).start();
        new Thread(() -> exampleBean.barrier()).start();

//        new Thread(() -> exampleBean.semaphore()).start();
//        new Thread(() -> exampleBean.semaphore()).start();
//        new Thread(() -> exampleBean.semaphore()).start();
//        new Thread(() -> exampleBean.semaphore()).start();
//        new Thread(() -> exampleBean.semaphore()).start();

    }

}
