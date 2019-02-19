package com.akash.problem;

import java.util.*;
/*

 */
public class CalculatorOperatorService {
    //set of valid operators
    Set<String> validOperator = new HashSet<>();
    Map<String,ExpressionService> serviceFinder = new HashMap<>();

    public ExpressionService find(char operator){
        if(!isValid(operator)){
            throw new InvalidExpressionException("INVALID EXPRESSION");
        }
        return serviceFinder.get(String.valueOf(operator));
    }

    //TODO:
    //We can abstract to a new object to register operator.
    public void registerExpressionService(char operator,ExpressionService expressionService){
        validOperator.add(String.valueOf(operator));
        serviceFinder.put(String.valueOf(operator),expressionService);
    }

    private boolean isValid(char operator){
      return  validOperator.contains(String.valueOf(operator));
    }
}
