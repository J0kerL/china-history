package com.history;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 中国历史知识系统 - 主启动类
 * @author Diamond
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.history.mapper")
public class ChinaHistoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChinaHistoryApplication.class, args);

        log.info("启动成功！");
    }

}
