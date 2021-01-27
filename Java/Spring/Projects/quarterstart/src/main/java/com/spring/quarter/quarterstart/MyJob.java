package com.spring.quarter.quarterstart;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Configurable
@EnableScheduling
public class MyJob {
    @Scheduled(fixedRate = 1000*5)
    public void reportCurrentTime(){
        System.out.println("Fixed Time:"+new Date());
    }
    @Scheduled(cron="*/5 * *  * * *")
    public void reportCurrentByCron(){
        System.out.println("reportCurrentByCron Time:"+new Date());
    }
}
