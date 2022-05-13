package com.xubo.druid.hongbao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author xubo
 * @Date 2022/5/13 17:23
 */
public class SendHBPro {

    private static Logger logger = LoggerFactory.getLogger(SendHBPro.class);

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        List<BigDecimal> hbList = mulSend(5, BigDecimal.valueOf(100));
        System.out.println("hbList.size() = " + hbList.size());
    }

    public static List<BigDecimal> mulSend(int num, BigDecimal money) {
        Future<List<BigDecimal>> submitList = executorService.submit(() -> {
            return sendHB(5, BigDecimal.valueOf(100));
        });
        List<BigDecimal> hbList = null;
        try {
            hbList = submitList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("hbList = " + hbList);
        return hbList;
    }

    public static List<BigDecimal> sendHB(int num, BigDecimal money) {
        // 已发
        BigDecimal distubuteMoney = BigDecimal.ZERO;
        // 余额
        BigDecimal overageMoney = money;
        List<BigDecimal> hbList = new ArrayList<>();
        synchronized ((Object) num) {
            while (num > 0) {
                num--;
                BigDecimal v;
                if (num != 0) {
                    v = RandomUtil.randomBigDecimal(BigDecimal.ZERO, overageMoney).setScale(2, RoundingMode.HALF_UP);
                } else {
                    v = money.subtract(distubuteMoney);
                }
                distubuteMoney = distubuteMoney.add(v);
                overageMoney = money.subtract(distubuteMoney);
                logger.info("单个红包金额：" + v + " 已发金额：" + distubuteMoney + " 余额：" + overageMoney);
                hbList.add(v);
            }
        }
        return hbList;
    }
}
