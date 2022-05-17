package com.xubo.druid.thread;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author xubo
 * @Date 2022/5/17 13:33
 * 异步执行，同步返回
 * 参考博客：https://blog.csdn.net/teachy/article/details/104971814
 */
@Data
@AllArgsConstructor
public class FutureTaskWorker<T,R> {

    /**
     * 需要异步执行的任务
     */
    private List<T> taskList;

    /**
     * 需要执行的方法
     */
    private Function<T, CompletableFuture<R>> workFunction;

    /**
     * 搜集执行结果
     * @return
     */
    public List<R> getAllResult() {
        List<CompletableFuture<R>> futureList = taskList.stream().map(workFunction).collect(Collectors.toList());
        // 等所有线程处理完再，合并所有结果集  跟 countdownLatch 类似,
        CompletableFuture<Void> allCompletableFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        return allCompletableFuture.thenApply(e -> futureList.stream().map(CompletableFuture::join).collect(Collectors.toList())).join();
    }



}
