package com.xubo.druid.validate;

import com.xubo.druid.scenes.entity.Movie;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author xubo
 * @Date 2022/1/6 14:00
 * 自己也可以封装方法 一般就是校验 null 和 空字符串
 */
public class ValidateNull {

    @Test
    public void validateParamNull() {
        List<Movie> list = new ArrayList<>();
        list.add(Movie.builder().title("yinianweidanwei").priceCode(52).build());
        list.add(Movie.builder().priceCode(43).build());
        list.add(Movie.builder().title("kuaguo").priceCode(23).build());
        list.add(Movie.builder().title("handong").priceCode(26).build());
        List<Integer> collect = list.stream().map(e -> e.getPriceCode()).collect(Collectors.toList());
        int i = 0;
        for (Movie movie : list) {
            Assert.notNull(movie.getTitle(),movie.getTitle() + "为空 : " + i);
            i++;
        }
        // 如果有异常就直接抛异常，不会执行该语句
        System.out.println("i" + i);

    }

    @Test
    public void testBlank() {
        System.out.println(StringUtils.isBlank(""));
        System.out.println(StringUtils.isBlank("test"));
    }

}
