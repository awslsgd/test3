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
    @DisplayName(value = "话费等价类测试")
    @ParameterizedTest
    @CsvFileSource(resources = "/话费等价类测试.csv", numLinesToSkip = 1, encoding = "UTF-8")
    void fileTest01(String Date1, String Date2, double expected) throws ParseException {

        PhoneFee p1 = new PhoneFee();

        long minutes = p1.CountTime(Date2,Date1);
        double money = p1.CountMoney(minutes);
        System.out.println("通话时间为："+minutes+"分钟");
        assertEquals(expected,money);
    }

    @Test
    @DisplayName(value="通话时间小于等于20分钟")
    void fileTest02() throws ParseException {
        PhoneFee p1 = new PhoneFee();
        double money = p1.CountMoney(10);
        assertEquals(0.5,money);
    }

    @Test
    @DisplayName(value="通话时间大于20分钟")
    void fileTest03() throws ParseException {
        PhoneFee p1 = new PhoneFee();
        double money = p1.CountMoney(25);
        assertEquals(1.5,money);
    }

    @Test
    @DisplayName(value="通话时间超过30个小时")
    void fileTest04() throws ParseException {
        PhoneFee p1 = new PhoneFee();
        double money = p1.CountMoney(30*60+1);
        assertEquals(-1.0,money);
    }

}
