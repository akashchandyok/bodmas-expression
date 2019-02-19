package com.akash.problem;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;


import java.math.BigDecimal;


public class BodmassExpressionEvaluatorTest {
    BodmassExpressionEvaluator bodmassExpressionEvaluator = new BodmassExpressionEvaluatorImpl();
    CalculatorOperatorService calculatorOperatorService = new CalculatorOperatorService();


    @Test
    public void testEvaluate_addition_sumOfIntegers() {
        //assign
        String expression = "22 + 22";
        Number expectedOutput = new Integer("44");
        //act
        Number actualOutput = bodmassExpressionEvaluator.evaluate(expression);
        //assert
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEvaluate_subtraction_subOfIntegers() {
        //assign
        String expression = "22 - 2";
        Number expectedOutput = new Integer("20");

        //act
        Number actualOutput = bodmassExpressionEvaluator.evaluate(expression);

        //assert
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEvaluate_multiply_mulOfIntegers() {
        //assign
        String expression = "22 * 2";
        Number expectedOutput = new Integer("44");
        //act
        Number actualOutput = bodmassExpressionEvaluator.evaluate(expression);
        //assert
        Assert.assertEquals(expectedOutput, actualOutput);
    }


    @Test
    public void testEvaluate_divide_divisionOfIntegers() {
        //assign
        String expression = "22 / 4";
        Number expectedOutput = new BigDecimal("5.50");
        //act
        Number actualOutput = bodmassExpressionEvaluator.evaluate(expression);

        //assert
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEvaluate_exponent_exponentOfIntegers() {
        //assign
        String expression = "4 ^ 3";
        Number expectedOutput = new Integer("64");
        //act
        Number actualOutput = bodmassExpressionEvaluator.evaluate(expression);
        //assert
        Assert.assertEquals(expectedOutput, actualOutput);
    }



    @Test
    public void testEvaluate_mixWithoutBraces_expressionSolved(){
        //assign
        String expression = "4 ^ 2 + 2 / 2 * 4 + 7 -8";
        Number expectedOutput = new Integer("19");
        //act
        Number actualOutput = bodmassExpressionEvaluator.evaluate(expression);
        //assert
        Assert.assertEquals(expectedOutput,actualOutput);
    }


    @Test(expected = InvalidExpressionException.class)
    public void testEvaluate_badOpertaror_expressionSolved() {
        //assign
        String expression = "4 & 2 + 2 / 2 * 4 + 7 -8";

        bodmassExpressionEvaluator.evaluate(expression);
    }

    @Test
    public void testEvaluate_mixWithBraces_expressionSolved() {
        //assign
        String expression = "7+(6*5^2+3-4/2)";
        Number expectedOutput = new Integer("158");
        //act
        Number actualOutput = bodmassExpressionEvaluator.evaluate(expression);
        //assert
        Assert.assertEquals(expectedOutput, actualOutput);
    }


    @Test
    public void testEvaluate_mixWithBraceAndOperator_solvedExpression() {
        //assign
        String expression = "(8*5/8)-(3/1)-5";
        Number expectedOutput = new Integer("-3");
        //act
        Number actualOutput = bodmassExpressionEvaluator.evaluate(expression);
        //assert
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test(expected = InvalidExpressionException.class)
    public void testEvaluate_mixWithInvalidExpression_expectException() {
        //assign
        String expression = "7+(67(56*2))";

        //act
        bodmassExpressionEvaluator.evaluate(expression);
    }

    @Test(expected = InvalidExpressionException.class)
    public void testEvaluate_mixWithTwoConsicutivesOperators_expectException() {
        //assign
        String expression = "8*/7";

        //act
        bodmassExpressionEvaluator.evaluate(expression);
    }
}
