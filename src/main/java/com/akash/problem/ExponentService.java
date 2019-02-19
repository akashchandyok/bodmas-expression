package com.akash.problem;

import java.math.BigDecimal;

public class ExponentService implements ExpressionService {
    @Override
    public BigDecimal evaluate(BigDecimal number1, BigDecimal number2) {
        BigDecimal multiplyNumber = new BigDecimal(number1.floatValue());
        for(int i = 1; i < number2.intValue(); i++){
            number1 = number1.multiply(multiplyNumber);
        }
        return number1;
    }
}
