package com.example.test3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PhoneFeeTest {
    @DisplayName(value = "话费等价类测试1")
    @ParameterizedTest
    @CsvFileSource(resources = "/话费等价类测试.csv", numLinesToSkip = 1, encoding = "UTF-8")
    void main(String Date1, String Date2, double expected) throws ParseException {
//        PhoneFee p1 = new PhoneFee();
//        long CountTime1=p1.CountTime(fromDate,toDate);
//        double money= p1.CountMoney(CountTime1);
        SimpleDateFormat simplefomat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        PhoneFee p1 = new PhoneFee();

        long minutes = p1.CountTime(Date2,Date1);
        double money = p1.CountMoney(minutes);
        System.out.println("通话时间为："+minutes+"分钟");
        assertEquals(expected,money);
    }
}
