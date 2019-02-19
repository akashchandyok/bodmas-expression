package com.akash.problem;

import java.math.BigDecimal;

public class DivisionService implements ExpressionService {
    @Override
    public BigDecimal evaluate(BigDecimal number1, BigDecimal number2) {
        return number1.divide(number2);
    }
}
