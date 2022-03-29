package com.xubo.druid.designmodel.singleten;

import com.xubo.druid.spring.beanlifecycle.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Author xubo
 * @Date 2022/3/25 17:12
 */
public class SingletenTest {

    /**
     * 用时创建
     */
    @Test
    public Student lazySingleten() {
         Student student = null;


        return student;
    }

    /**
     * 先创建
     */
    @Test
    public void hungrySingleten() {


    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        List<String> list = Arrays.asList("1","2");
        List<CompletableFuture<ValidateSynchronizedSingleten>> collect = list.stream().map(e -> CompletableFuture.supplyAsync(() -> {
            return ValidateSynchronizedSingleten.getInstance();
        }, pool)).collect(Collectors.toList());

        List<ValidateSynchronizedSingleten> singletens = collect.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(singletens.size());

    }




}
