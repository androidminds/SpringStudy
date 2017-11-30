package cn.androidminds.springstudy.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class AutoTask {
    @Scheduled(cron = "0/5 * * * * ?") // 每5秒执行一次
    public void scheduler() {
        System.out.println(">>>>>>>>> SchedulingConfig.scheduler()");
    }
}
