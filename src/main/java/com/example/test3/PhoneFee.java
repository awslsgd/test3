package com.example.test3;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.*;
import java.text.*;
import java.util.*;
public class PhoneFee {
    //计算时间的函数
    public long CountTime(String Date1, String Date2) throws ParseException {
        SimpleDateFormat simpleFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date fromDate = simpleFormat1.parse(Date1);
        Date toDate = simpleFormat1.parse(Date2);

        Calendar fromcal = Calendar.getInstance();
        fromcal.setTime(fromDate);
        Calendar tocal = Calendar.getInstance();
        tocal.setTime(toDate);

        long PhoneTime = fromcal.getTime().getTime() - tocal.getTime().getTime();
        long minutes;

        if(PhoneTime>=0 && PhoneTime/1000<60 || PhoneTime%60000>0) //不到一分钟按一分钟计算
            minutes = PhoneTime/1000/60+1;
        else
            minutes = PhoneTime/1000/60;


        if(fromcal.get(Calendar.MONTH) == Calendar.OCTOBER && fromcal.get(Calendar.DATE) >= 25 && fromcal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            //开始时间在十月最后一个周日
            if(fromcal.get(Calendar.HOUR_OF_DAY)==2 && tocal.get(Calendar.HOUR_OF_DAY)==2 && tocal.get(Calendar.HOUR_OF_DAY)<3 && tocal.get(Calendar.MINUTE)<=fromcal.get(Calendar.MINUTE)) {
                //因为转换而出现通话时间为负的情况
                minutes+=60;
            }
            else if(fromcal.get(Calendar.HOUR_OF_DAY)<2 && fromcal.get(Calendar.HOUR_OF_DAY)>=2) {
                //开始时间是两点前，结束时间是两点后（经历第二次转换
                minutes+=60;
            }
            else {
                ;         //什么也不做，minutes不变
            }

        }
        else{
            if(tocal.get(Calendar.MONTH) == Calendar.OCTOBER && tocal.get(Calendar.DATE) >= 25 && tocal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                if(tocal.get(Calendar.HOUR_OF_DAY)>=2)
                    minutes +=60;
            }
            else {
                if((fromcal.get(Calendar.MONTH) == Calendar.MARCH && fromcal.get(Calendar.DATE) >= 25 && fromcal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)){
                    //开始时间在三月最后一个周日
                    if(fromcal.get(Calendar.HOUR_OF_DAY)<2 && tocal.get(Calendar.HOUR_OF_DAY)>=3)
                        //开始时间在两点前，结束时间在三点后（经过了第一次转换
                        minutes -=60;
                }
                else{//开始时间不在三月最后一个周日
                    if((tocal.get(Calendar.MONTH) == Calendar.MARCH && tocal.get(Calendar.DATE) >= 25 && tocal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)){
                        //但是结束时间在（从前一天打到这一天
                        if(tocal.get(Calendar.HOUR_OF_DAY)>=3 )
                            minutes -= 60;
                    }
                }
            }
        }
        return minutes;
    }

    public double CountMoney(long time){
        double money = 0;
        if(time <= 0|| time > 30){
            money = -1.0; //返回负数表示报错
        }
        else if(time > 0 && time <=20){
            money = time*0.05;
        }
        else
            money = 1+(time - 20)*0.1;
        return money;
    }
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fromtime1 = br.readLine();
            String totime1 = br.readLine();

            SimpleDateFormat simplefomat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            PhoneFee p1 = new PhoneFee();

            long minutes = p1.CountTime(fromtime1,totime1);
            double money = p1.CountMoney(minutes);

            System.out.println(money);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
