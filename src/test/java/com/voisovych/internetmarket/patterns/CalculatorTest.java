package com.voisovych.internetmarket.patterns;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void additionSuccessful() {
        Calculator calculator = new Calculator();
        int res = calculator.calc(2, 2, true);
        Assert.assertEquals(4, res);
    }

    @Test
    public void subtractionSuccessful() {
        Calculator calculator = new Calculator();
        int res = calculator.calc(2, 2, false);
        Assert.assertEquals(0, res);
    }

    @Test
    public void logSuccessful () {
        Calculator calculator = new Calculator();
        Double res =  calculator.log(12, 144);
        Assert.assertEquals((Double) 2d, res);
    }

}