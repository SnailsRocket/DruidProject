package com.xubo.druid.thread;

import com.google.common.collect.Lists;
import com.xubo.druid.entity.domain.Student;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Author xubo
 * @Date 2022/2/15 9:14
 */
public class MulProcessListTest {

    @Test
    public void processListByOrder() {
        List<Student> studentList = Arrays.asList(Student.builder().ssex("nan").sname("xubo1").build(),
                Student.builder().ssex("nan").sname("xubo2").build(),
                Student.builder().ssex("nan").sname("xubo3").build(),
                Student.builder().ssex("nan").sname("xubo4").build(),
                Student.builder().ssex("nan").sname("xubo5").build(),
                Student.builder().ssex("nan").sname("xubo6").build(),
                Student.builder().ssex("nan").sname("xubo7").build(),
                Student.builder().ssex("nan").sname("xubo8").build(),
                Student.builder().ssex("nan").sname("xubo9").build(),
                Student.builder().ssex("nan").sname("xubo10").build(),
                Student.builder().ssex("nan").sname("xubo11").build(),
                Student.builder().ssex("nan").sname("xubo12").build());

        List<List<Student>> partitionList = Lists.partition(studentList, 4);
        List<Student> resultList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("ss SS");
        long startTime = System.currentTimeMillis();
        partitionList.stream().forEach(e -> {
            e.stream().forEach(o -> {
                o.setSsex("nv");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            });
            resultList.addAll(e);
        });
        long endTime = System.currentTimeMillis();
        System.out.println(sdf.format(endTime - startTime));
    }


    @Test
    public void mulProcessList() {
        List<Student> studentList = Arrays.asList(Student.builder().ssex("nan").sname("xubo1").build(),
                Student.builder().ssex("nan").sname("xubo2").build(),
                Student.builder().ssex("nan").sname("xubo3").build(),
                Student.builder().ssex("nan").sname("xubo4").build(),
                Student.builder().ssex("nan").sname("xubo5").build(),
                Student.builder().ssex("nan").sname("xubo6").build(),
                Student.builder().ssex("nan").sname("xubo7").build(),
                Student.builder().ssex("nan").sname("xubo8").build(),
                Student.builder().ssex("nan").sname("xubo9").build(),
                Student.builder().ssex("nan").sname("xubo10").build(),
                Student.builder().ssex("nan").sname("xubo11").build(),
                Student.builder().ssex("nan").sname("xubo12").build());

        List<List<Student>> partitionList = Lists.partition(studentList, 3);
        System.out.println(partitionList.size());
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        SimpleDateFormat sdf = new SimpleDateFormat("ss SS");
        long startTime = System.currentTimeMillis();
        List<CompletableFuture<List<Student>>> completableFutureList = partitionList.stream().map(e ->  CompletableFuture.supplyAsync(() -> {
            e.stream().forEach(o -> {
                o.setSsex("nv");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            });
            System.out.println(Thread.currentThread().getName());
            return e;
        }, executorService)).collect(Collectors.toList());
        List<List<Student>> collectList = completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        List<Student> students = collectList.stream().flatMap(inner -> inner.stream()).collect(Collectors.toList());
        long endTime = System.currentTimeMillis();
        System.out.println(sdf.format(endTime - startTime));
        System.out.println(students);

    }
}
