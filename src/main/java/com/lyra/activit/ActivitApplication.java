package com.lyra.activit;

import org.activiti.core.common.spring.identity.config.ActivitiSpringIdentityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {})
public class ActivitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitApplication.class, args);
    }

}
