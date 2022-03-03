package com.xubo.druid.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @Author xubo
 * @Date 2022/3/3 15:11
 */
@Configuration
@Slf4j
public class RabbitMQConfig {

    public static void main(String[] args) {
        String str = "http://buy.ccb.com/openLoginInterface.jhtml?channelNo=test1&toUrl=http%3A%2F%2Fbuy.ccb.com%2Fclient%2Findex.jhtml%3Faaa%3Dxxx%26bbb%3Dxxx&loginParams=CB3248B227DE22619AE8891AAF2035B5960838FB0DD7887A0D97C633A8A31B403E9AE13CC8333674F3E2795EBC62133E54624E928C91F57E0CB7E3820EABAF1D63C7EEBBB90273433D8A7ADCC17250A322BAD2FCB39E15B179BB503236065AD7C6B50F888062242AD50CFBD7DEFF932221B6397E940D71CB05F0001399B4DEF4CDB13571B15EE659BFE1533C2EE517C0A442AA123D7F8D2C9A7889197D9289CED83464AAF6C414F34673E1DCF182AABA&requestTime=1646298319515&tranSid=1646298319515";
        System.out.println(str.length());
    }

}
