package com.xubo.druid.hongbao;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Author xubo
 * @Date 2022/5/13 10:00
 * 需要优化
 *  暂时不考虑以下场景
 *  红包个数 校验
 *  需要上锁，对num加锁
 *      场景：
 *      如果num=1时，多个线程都进入到 while里面，生成多个最后红包，已发大于红包，余额为负数，金额对不上
 *      num>1时，多个线程进入while里面,生成多个红包，已发可能大于红包中金额，这样会导致余额小于0，已发大于红包金额，最后一个红包为负数，红包超发(100 5个的红包，发了150 8个)
 *  红包金额 金额校验
 *  金额校验可以解决并发问题，建议加锁
 *  单个红包最低 0.01的情况校验
 *  并发情况下的处理
 *  并发会导致很多问题，参考个数校验
 *  红包第一个生成
 *  ...
 * <p>
 * 思路：
 * 入参就两个 红包个数 红包金额
 */
public class SendHB {

    private static Logger logger = LoggerFactory.getLogger(SendHB.class);

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        // 正常情况下，单线程执行
        /*List<BigDecimal> hbList = hb(5, BigDecimal.valueOf(100));
        System.out.println("hbList: "+ hbList);
        double totalMoney = hbList.stream().collect(Collectors.summingDouble(x -> {
            return Double.valueOf(String.valueOf(x));
        })).doubleValue();
        System.out.println("totalMoney = " + totalMoney);*/

        // 并发情况下
        /*List<BigDecimal> hbList = null;
        try {
            hbList = hb(5, BigDecimal.valueOf(100));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hbList = " + hbList);*/

        List<JSONObject> paramList = new ArrayList<JSONObject>(){{
            add(new JSONObject().fluentPut("num", 5).fluentPut("money", BigDecimal.valueOf(100)));
        }};
        List<CompletableFuture<List<BigDecimal>>> completableFutureList = paramList.stream().map(e -> CompletableFuture.supplyAsync(() -> {
            List<BigDecimal> hbList = null;
            try {
                hbList = hb(e.getIntValue("num"), e.getBigDecimal("money"));
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return hbList;
        }, executorService)).collect(Collectors.toList());
        List<List<BigDecimal>> collectList = completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        List<BigDecimal> filterList = collectList.stream().flatMap(inner -> inner.stream()).collect(Collectors.toList());
        System.out.println("filterList = " + filterList);
    }

    public static List<BigDecimal> hb(int num, BigDecimal money) throws ExecutionException, InterruptedException {
        Future<List<BigDecimal>> submitList = executorService.submit(() -> {
            return processHB(num, money);
        });
        return submitList.get();
    }

    public static List<BigDecimal> processHB(int num, BigDecimal money) {
        // TODO 省略红包个数，红包金额，单个红包最低金额 0.01的情况校验
        ArrayList<BigDecimal> hbList = new ArrayList<>();
        // 余额
        BigDecimal overageMoney = money;
        // 已分发的金额
        BigDecimal distubuteMoney = new BigDecimal(0);
        while (num > 0) {
            // while (overageMoney.compareTo(BigDecimal.ZERO) <= 0) { // 错误思路 需要用个数来控制循环，这边需要区分最后一个红包的逻辑
            num--;
            // 记录每个红包的金额
            BigDecimal v = BigDecimal.ZERO;
            if (num != 0) {
                // double 可以这样操作，但是BigDecimal不可以这样生成
//                v = ThreadLocalRandom.current().nextDouble(0, money);
                v = RandomUtil.randomBigDecimal(BigDecimal.ZERO, overageMoney).setScale(2, RoundingMode.HALF_UP);
            } else {
                // 最后一个红包
                v = money.subtract(distubuteMoney);
            }
            distubuteMoney = distubuteMoney.add(v);
            overageMoney = money.subtract(distubuteMoney);
            logger.info("单个金额：" + v.toString() + " --余额：" + overageMoney + " --已发金额：" + distubuteMoney);
            hbList.add(v);
        }
        logger.info("余额：" + overageMoney);
        logger.info("已发金额：" + distubuteMoney);
        return hbList;
    }
}
