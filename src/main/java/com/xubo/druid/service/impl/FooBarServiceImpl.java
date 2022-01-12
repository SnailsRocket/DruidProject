package com.xubo.druid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xubo.druid.entity.domain.FooBar;
import com.xubo.druid.mapper.FooBarMapper;
import com.xubo.druid.service.FooBarService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class FooBarServiceImpl extends ServiceImpl<FooBarMapper, FooBar>
    implements FooBarService {

}




