package com.hzvtc.lujc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: lujc
 * @date: 2019/11/14/014 9:41
 */
@SpringBootApplication
@MapperScan("com.hzvtc.lujc.moudules.bsUser.mapper")
public class BsVueProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(BsVueProjectApplication.class, args);
    }
}
