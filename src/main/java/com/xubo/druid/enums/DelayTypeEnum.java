package com.xubo.druid.enums;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @Author xubo
 * @Date 2022/4/2 14:29
 */
@Getter
public enum DelayTypeEnum {

    DELAY_10s(1, 10000),
    DELAY_60s(2, 60000);

    private Integer id;
    private Integer min;

    DelayTypeEnum(Integer id, Integer min) {
        this.id = id;
        this.min = min;
    }


    public static DelayTypeEnum getDelayTypeEnumByValue(Integer delayType) {
        if(StringUtils.isEmpty(delayType)) return null;
        return Arrays.asList(DelayTypeEnum.values()).stream().filter(e -> e.getId().equals(delayType)).findFirst().get();
    }
}
