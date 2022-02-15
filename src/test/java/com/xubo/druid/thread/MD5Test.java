package com.xubo.druid.thread;

import org.junit.Test;
import org.springframework.util.DigestUtils;

/**
 * @Author xubo
 * @Date 2022/2/14 11:41
 */
public class MD5Test {

    @Test
    public void testMD5() {
        String str = "body=Body&fee_type=1&input_charset=utf-8&out_trade_no=aa123456789012345678901234567890&partner=120105278&service=pay_service&show_url=http://www.baidu.com&spbill_create_ip=127.0.0.1&subject=subject&total_fee=100&trade_mode=0002&trans_channel=pc&7f8f7ca3569c4e6e98a36b2845c014a1";
        String s = DigestUtils.md5DigestAsHex(str.getBytes());
        System.out.println(s);

        String str1 = "b98b8ceead0af455beed0543dd116449";
        System.out.println(str1.length());

        if(!str1.equals(null)) {
            System.out.println(false);
        }


    }


}
