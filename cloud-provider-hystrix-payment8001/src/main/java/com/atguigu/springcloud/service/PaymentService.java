package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id) {
        return "ok, thread: " + Thread.currentThread().getName() + ", id:" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler")
    public String paymentInfo_Timeout(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "error, thread: " + Thread.currentThread().getName() + ", id:" + id;
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "error, ok, thread: " + Thread.currentThread().getName() + ", id:" + id;
    }
}
