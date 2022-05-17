package com.xubo.druid.thread;

import com.xubo.druid.entity.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author xubo
 * @Date 2022/5/17 10:30
 * ThreadLocal 详解
 * ThreadLocal 叫做线程变量,意思是ThreadLocal中填充的变量属于当前线程，该变量对其他线程而言是隔离的，也就是说该变量是当前线程独有的
 */
public class ThreadLocalDemo {

    private static ThreadLocal<Student> threadLocal = new ThreadLocal<>();

    private static Logger logger = LoggerFactory.getLogger(ThreadLocal.class);

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);


    public static void main(String[] args) {
        setThreadLocalParam();
    }

    /**
     *
     */
    public static void setThreadLocalParam() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Student student = new Student();
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            student.setSsex("man");
            return Arrays.asList(1, 2, 3, 4, 5);
        }, executorService).thenAcceptAsync((items) -> {
            student.setSid("12");
        }, executorService);

        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            student.setSbirth("2022.5.17");
        }, executorService);
        try {
            CompletableFuture.allOf(completableFuture, completableFuture1).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
