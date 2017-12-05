package cn.androidminds.springstudy.service;

import cn.androidminds.springstudy.GlobalDefaultExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class AutoTask {
    private Logger logger = LoggerFactory.getLogger(AutoTask.class);
    @Scheduled(cron = "0/5 * * * * ?") // 每5秒执行一次
    public void scheduler() {
        logger.info(">>>>>>>>> SchedulingConfig.scheduler()");
    }
}
