package com.xubo.druid.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xubo.druid.mapper.ScoreMapper;
import com.xubo.druid.service.ScoreService;
import com.xubo.druid.entity.domain.Score;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
@Service
@Slf4j
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score>
    implements ScoreService {

    public JSONObject testLock() {

        ReentrantLock reentrantLock = new ReentrantLock(true);

        if(reentrantLock.tryLock()) {

        }



        return null;
    }

    public List<Score> getLists() {
        ReentrantLock reentrantLock = new ReentrantLock(true);
        try {
            if(reentrantLock.tryLock()) {
                List<Score> result = this.list();
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("getLists 释放锁！");
        }
        /*ReentrantLock reentrantLock = new ReentrantLock(true);
        try {
            if(reentrantLock.tryLock()) {
                List<Score> result = this.list();
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("getLists 释放锁！");
            reentrantLock.unlock();
        }*/
        return null;
    }

}




