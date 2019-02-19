package com.akash.problem;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class AddtionServiceTest {

    ExpressionService expressionService = new AddtionService();

    @Test
    public void testEvaluate_validNumber_sum(){
        //assign
        BigDecimal number1 = new BigDecimal(1.0);
        BigDecimal number2 = new BigDecimal(1.0);
        BigDecimal expectedNumber = new BigDecimal(2.0);
        //action
        BigDecimal actualNumber = expressionService.evaluate(number1,number2);

        //assert
        Assert.assertEquals(expectedNumber,actualNumber);
    }
}
