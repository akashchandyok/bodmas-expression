package com.akash.problem;

import org.junit.Test;


import static com.akash.problem.CalculatorServiceUtil.hasPrecedence;
import static org.junit.Assert.*;

public class CalculatorServiceUtilTest {

    @Test
    public void testhasPrecedence_operator2Greater_true(){
        //assign
        char x = '+';
        char y = '*';

        assertTrue(CalculatorServiceUtil.hasPrecedence(x,y));
    }

    @Test
    public void testhasPrecedence_equal_true(){
        //assign
        char x = '+';
        char y = '-';

        assertTrue(CalculatorServiceUtil.hasPrecedence(x,y));
    }

    @Test
    public void testhasPrecedence_firstBraceOperator_true(){
        //assign
        char x = '(';
        char y = '-';

        assertTrue(CalculatorServiceUtil.hasPrecedence(x,y));
    }

    @Test
    public void testhasPrecedence_SecondBraceOperator_false(){
        //assign
        char x = '*';
        char y = ')';

        assertFalse(CalculatorServiceUtil.hasPrecedence(x,y));
    }

    @Test
    public void testhasPrecedence_SecondExponentOperator_true(){
        //assign
        char x = '*';
        char y = '^';

        assertTrue(CalculatorServiceUtil.hasPrecedence(x,y));
    }

    @Test
    public void testhasPrecedence_FirstExponentOperator_false(){
        //assign
        char x = '^';
        char y = '*';

        assertFalse(CalculatorServiceUtil.hasPrecedence(x,y));
    }


    @Test
    public void testhasPrecedence_FirstOperatorGreater_false(){
        //assign
        char x = '*';
        char y = '+';

        assertFalse(CalculatorServiceUtil.hasPrecedence(x,y));
    }

}