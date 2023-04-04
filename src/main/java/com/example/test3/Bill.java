package com.example.test3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bill {
    long calculateTimeSpan(LocalDateTime startTime, LocalDateTime endTime){
        java.time.Duration duration = java.time.Duration.between(startTime,endTime);
            long millis = duration.toMillis();//毫秒
            long minutes = duration.toMinutes();//分钟
            long hours = duration.toHours();//小时
            long days = duration.toDays();//天数
            long timeLength=minutes+hours*60+days*24*60;
            if(millis>0)
                timeLength++;
            return timeLength;
    }

    double calculateFee(long timeLength){
            double money = 0;
            if(timeLength <= 0|| timeLength > 30*60){
                money = -1.0; //返回负数表示报错
            }
            else if(timeLength > 0 && timeLength <=20){
                money = timeLength*0.05;
            }
            else
                money = 1+(timeLength - 20)*0.1;
            return money;
        }

//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        LocalDateTime time1 = LocalDateTime.parse(br.readLine());
//        LocalDateTime time2 = LocalDateTime.parse(br.readLine());
//        Bill bill=new Bill();
//        Bill.calculateTimeSpan(time1,time2);
//    }

    }


