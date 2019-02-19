package com.akash.problem;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Stack;

import static com.akash.problem.CalculatorServiceUtil.hasPrecedence;

/*
Main service class for Epression calculator based on BODMAS rule.
 */
public class BodmassExpressionEvaluatorImpl implements BodmassExpressionEvaluator {

    //TODO:
    //this can be injected through Factory or dependency injection framework like Spring or Google guice
    private CalculatorOperatorService calculatorOperatorService = new CalculatorOperatorService();

    public Number evaluate(String expression) {
        //TODO:
        // we can regisgter the different services at run time
        // a new class should be responsible for this, registering the required services.
        //Properties file can also be used to configured this service
        //or if using sprint, in configuration class, we can let spring know which all service required to evaluate the expression
        // Any new operator will required to evaluate the statement will not change the service class
        calculatorOperatorService.registerExpressionService('+',new AddtionService());
        calculatorOperatorService.registerExpressionService('-',new SubtractionService());
        calculatorOperatorService.registerExpressionService('*',new MultiplyService());
        calculatorOperatorService.registerExpressionService('/',new DivisionService());
        calculatorOperatorService.registerExpressionService('^',new ExponentService());


        Number returnValue = new BigDecimal(0);

        char[] chars = expression.toCharArray();
        Stack<Character> operators = new Stack<>();
        Stack<BigDecimal> numbers = new Stack<>();

        try {
            for (int i = 0; i < chars.length; i++) {
                // it is a whitespace, let's skip it
                if ( String.valueOf(chars[i]).trim().isEmpty()) {
                    continue;
                }


                if (chars[i] == '(') {
                    if(i!=0){
                        int k = i -1;
                        if(chars[k] >= '0' && chars[i] <= '9'){
                            throw new InvalidExpressionException("INVALID EXPRESSION");
                        }
                    }
                    operators.push(chars[i]);
                    continue;
                }
                //let's solve the inner expression as a expression
                if (chars[i] == ')') {
                    while (operators.peek() != '(') {
                        numbers.push(evaluate(operators.pop(), numbers.pop(), numbers.pop()));
                    }
                    operators.pop();
                    continue;
                }

                // if number, let's push into number stack
                if (chars[i] >= '0' && chars[i] <= '9') {
                    StringBuffer sb = new StringBuffer();
                    int k = i;
                    //let check if there is further number after this character
                    while (k < chars.length && chars[k] >= '0' && chars[k] <= '9') {
                        sb.append(chars[k]);
                        k++;
                    }
                    numbers.push(BigDecimal.valueOf(Double.parseDouble(sb.toString())));
                    if(k != i+1){
                        i = k-1;
                    }
                }
                // encountered operator.
                // futher operator could have greater precedence.
                else {
                    int j = i+1;
                    for(;j<chars.length;j++) {
                        if (!String.valueOf(chars[j]).trim().isEmpty()) {
                            break;
                        }
                    }
                    //no further operator at next element
                    if(!(chars[j] >= '0' &&  chars[j] <= '9') && (chars[j] != '(' && chars[j] != ')') ) {
                        throw new InvalidExpressionException("INVALID EXPRESSION");
                    }
                    while (!operators.empty() && hasPrecedence(chars[i], operators.peek())) {
                        numbers.push(evaluate(operators.pop(), numbers.pop(), numbers.pop()));
                    }
                    operators.push(chars[i]);
                }
            }
            while (!operators.empty()) {

                numbers.push(evaluate(operators.pop(), numbers.pop(), numbers.pop()));
            }

        } catch (InvalidExpressionException ex) {
            throw ex;
        }



        BigDecimal nonFormattedOutPut =   numbers.pop();
        if(nonFormattedOutPut.stripTrailingZeros().scale() <= 0){
          return nonFormattedOutPut.intValue();
        }
        return nonFormattedOutPut.setScale(2,BigDecimal.ROUND_HALF_UP);

    }


    private BigDecimal evaluate(char operator, BigDecimal b, BigDecimal a)
    {
        ExpressionService expressionService = calculatorOperatorService.find(operator);
        if(expressionService != null) {
            return expressionService.evaluate(a, b);
        }
        throw new InvalidExpressionException("INVALID EXPRESSION");
    }
}
