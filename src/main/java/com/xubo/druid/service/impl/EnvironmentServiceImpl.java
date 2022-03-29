package com.xubo.druid.service.impl;

import com.xubo.druid.service.EnvironmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @Author xubo
 * @Date 2022/3/29 13:57
 */
@Service
@Slf4j
public class EnvironmentServiceImpl implements EnvironmentService {

    @Autowired
    Environment environment;

    @Override
    public String getActiveProfiles() {
        String[] activeProfiles = environment.getActiveProfiles();
        log.info(activeProfiles[0]);
        return activeProfiles[0];
    }
}
