package com.xubo.druid.interview;

import com.google.common.collect.Lists;
import com.xubo.druid.interview.entity.ActivityBo;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author xubo
 * @Date 2021/12/30 11:29
 *
 * test1
 *    问题二 给定了500场活动在数组activityList中（未按开始时间排序），现需要给用户展示最近7天内开始及当日进行中的活动，并按开始时间升序排序展示。
 *       let activityList=[{
 *       actName: '活动名称',
 *       actStartDate: 'yyyy-MM-dd', // 活动开始日期，为字符串
 *       }]
 *
 * test2
 *   //问题四（二选一）：
 *       A、启2个线程，使之交替打印1-100,如：两个线程分别为：Printer1和Printer2,最后输出结果为： Printer1 — 1 Printer2 一 2 Printer1 一 3 Printer2 一 4
 *
 *       B、启5个线程，并发对1-10000之间的数字进行求和，并打印结果。（要求5个线程全部计算完再一次性汇总）
 *
 */
public class AliInterviewTest {


    /**
     * 问题二 给定了500场活动在数组activityList中（未按开始时间排序），现需要给用户展示最近7天内开始及当日进行中的活动，并按开始时间升序排序展示。
     *    let activityList=[{
     *    actName: '活动名称',
     *    actStartDate: 'yyyy-MM-dd', // 活动开始日期，为字符串
     *    }]
     *
     */
    @Test
    public void getOrderActivity() {
        List<ActivityBo> activityBos = fillActityBo();
        List<ActivityBo> filterList = activityBos.stream().filter(e -> formatStringToDate(e.getActStartDate()).after(getBeforeDay()))
                .sorted(Comparator.comparing(ActivityBo::getActStartDate)).collect(Collectors.toList());
        System.out.println(filterList.size());
    }

    /**
     *  A、启2个线程，使之交替打印1-100,如：两个线程分别为：Printer1和Printer2,最后输出结果为： Printer1 — 1 Printer2 一 2 Printer1 一 3 Printer2 一 4
     *  B、启5个线程，并发对1-10000之间的数字进行求和，并打印结果。（要求5个线程全部计算完再一次性汇总）
     *
     */
    @Test
    public void concurrentPrint() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadRun" + Thread.currentThread().getName());
            }
        });

    }

    public Date getBeforeDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        Date beforeDate = calendar.getTime();
        return beforeDate;
    }

    public Date formatStringToDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public List<ActivityBo> fillActityBo() {
        List<ActivityBo> actList = new ArrayList<>();
        return Arrays.asList(ActivityBo.builder().activity("活动1").actStartDate("2021-12-30 12:12:12").build(),
                ActivityBo.builder().activity("活动2").actStartDate("2021-12-10 12:12:12").build(),
                ActivityBo.builder().activity("活动3").actStartDate("2021-12-15 12:12:12").build(),
                ActivityBo.builder().activity("活动4").actStartDate("2021-12-11 12:12:12").build(),
                ActivityBo.builder().activity("活动5").actStartDate("2021-12-25 12:12:12").build(),
                ActivityBo.builder().activity("活动6").actStartDate("2021-12-02 12:12:12").build(),
                ActivityBo.builder().activity("活动7").actStartDate("2021-12-26 12:12:12").build(),
                ActivityBo.builder().activity("活动8").actStartDate("2021-12-27 12:12:12").build(),
                ActivityBo.builder().activity("活动9").actStartDate("2021-12-28 12:12:12").build(),
                ActivityBo.builder().activity("活动10").actStartDate("2021-12-29 12:12:12").build(),
                ActivityBo.builder().activity("活动11").actStartDate("2021-12-28 12:12:12").build(),
                ActivityBo.builder().activity("活动12").actStartDate("2021-12-11 12:12:12").build(),
                ActivityBo.builder().activity("活动13").actStartDate("2021-12-15 12:12:12").build(),
                ActivityBo.builder().activity("活动14").actStartDate("2021-12-17 12:12:12").build(),
                ActivityBo.builder().activity("活动15").actStartDate("2021-12-30 12:12:12").build());
    }

}
