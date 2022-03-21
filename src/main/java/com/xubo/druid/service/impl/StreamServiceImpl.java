package com.xubo.druid.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xubo.druid.entity.bo.GoodsInfo;
import com.xubo.druid.service.StreamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author xubo
 * @Date 2022/3/21 10:57
 */
@Service
@Slf4j
public class StreamServiceImpl implements StreamService {

    @Override
    public JSONObject reduce(JSONObject jsonObject) {
        List<GoodsInfo> goodsInfoList = fillData();
        Integer initPrice = Integer.valueOf(10);
        BigDecimal initBigDecimal = BigDecimal.TEN;
        BigDecimal totalPrice = goodsInfoList.stream().map(GoodsInfo::getInitialPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        log.info(totalPrice.toString());

        goodsInfoList.stream().forEach(e -> {
            BigDecimal updateInitPrice = e.getInitialPrice().add(initBigDecimal);
            e.setInitialPrice(updateInitPrice);
        });
        log.info(goodsInfoList.toString());


        return new JSONObject().fluentPut("totalPrice", totalPrice);
    }

    @Override
    public JSONObject peek(JSONObject jsonObject) {
        List<String> filterList = Stream.of("one", "two", "fthree", "four", "five").filter(e -> e.length() > 3)
                .peek(e -> System.out.println("filter value: "  + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("upperCase: " + e))
                .peek(e -> e.replace("f", "D"))
                .collect(Collectors.toList());
        log.info(filterList.toString());
        List<GoodsInfo> goodsInfoList = Stream.of(GoodsInfo.builder().name("Druid").build(),
                GoodsInfo.builder().name("Test1").build(),
                GoodsInfo.builder().name("Xubo").build())
                .peek(e -> System.out.println("peek before: " + e))
                .peek(e -> e.setName("kkk"))
                .peek(e -> System.out.println("after peek: " + e))
                .collect(Collectors.toList());
        log.info(goodsInfoList.toString());
        return null;
    }

    public List<GoodsInfo> fillData() {
        List<GoodsInfo> goodsInfoList = Arrays.asList(
                GoodsInfo.builder().id(1).name("Druid").description("first goods").initialPrice(BigDecimal.valueOf(Double.valueOf(35)))
                        .discountPrice(BigDecimal.valueOf(Double.valueOf(15))).remainPrice(BigDecimal.valueOf(Double.valueOf(8))).build(),
                GoodsInfo.builder().id(2).name("Druid1").description("first goods").initialPrice(BigDecimal.valueOf(Double.valueOf(23)))
                        .discountPrice(BigDecimal.valueOf(Double.valueOf(8))).remainPrice(BigDecimal.valueOf(Double.valueOf(5))).build(),
                GoodsInfo.builder().id(3).name("Druid2").description("first goods").initialPrice(BigDecimal.valueOf(Double.valueOf(39)))
                        .discountPrice(BigDecimal.valueOf(Double.valueOf(16))).remainPrice(BigDecimal.valueOf(Double.valueOf(7))).build(),
                GoodsInfo.builder().id(4).name("Druid3").description("first goods").initialPrice(BigDecimal.valueOf(Double.valueOf(43)))
                        .discountPrice(BigDecimal.valueOf(Double.valueOf(29))).remainPrice(BigDecimal.valueOf(Double.valueOf(9))).build(),
                GoodsInfo.builder().id(5).name("Druid4").description("first goods").initialPrice(BigDecimal.valueOf(Double.valueOf(27)))
                        .discountPrice(BigDecimal.valueOf(Double.valueOf(11))).remainPrice(BigDecimal.valueOf(Double.valueOf(3))).build(),
                GoodsInfo.builder().id(6).name("Druid5").description("first goods").initialPrice(BigDecimal.valueOf(Double.valueOf(76)))
                        .discountPrice(BigDecimal.valueOf(Double.valueOf(22))).remainPrice(BigDecimal.valueOf(Double.valueOf(2))).build(),
                GoodsInfo.builder().id(7).name("Druid6").description("first goods").initialPrice(BigDecimal.valueOf(Double.valueOf(64)))
                        .discountPrice(BigDecimal.valueOf(Double.valueOf(25))).remainPrice(BigDecimal.valueOf(Double.valueOf(1))).build(),
                GoodsInfo.builder().id(8).name("Druid7").description("first goods").initialPrice(BigDecimal.valueOf(Double.valueOf(34)))
                        .discountPrice(BigDecimal.valueOf(Double.valueOf(9))).remainPrice(BigDecimal.valueOf(Double.valueOf(8))).build(),
                GoodsInfo.builder().id(9).name("Druid8").description("first goods").initialPrice(BigDecimal.valueOf(Double.valueOf(42)))
                        .discountPrice(BigDecimal.valueOf(Double.valueOf(12))).remainPrice(BigDecimal.valueOf(Double.valueOf(7))).build(),
                GoodsInfo.builder().id(10).name("Druid9").description("first goods").initialPrice(BigDecimal.valueOf(Double.valueOf(73)))
                        .discountPrice(BigDecimal.valueOf(Double.valueOf(33))).remainPrice(BigDecimal.valueOf(Double.valueOf(11))).build()
                );
        return goodsInfoList;
    }
}
