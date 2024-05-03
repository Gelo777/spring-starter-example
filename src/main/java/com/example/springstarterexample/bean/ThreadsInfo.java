package com.example.springstarterexample.bean;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

@Slf4j
public class ThreadsInfo {

    private final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    public void printThreadsInfo() {
        ThreadInfo[] allThreads = threadMXBean.dumpAllThreads(true, true);
        for (ThreadInfo threadInfo : allThreads) {
            log.info("ID Потока: {}", threadInfo.getThreadId());
            log.info("Имя Потока: {}", threadInfo.getThreadName());
            log.info("Статус Потока: {}", threadInfo.getThreadState());
            log.info("----------------------------------------");
        }
    }
}
