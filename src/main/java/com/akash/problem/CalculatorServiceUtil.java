package com.akash.problem;

/*
Util class to support calculator service.
*/
public class CalculatorServiceUtil {

    /*
    true: if 'operator2' has higher or same precedence as 'operator1'
    */
    public static boolean hasPrecedence(char operator1, char operator2)
    {
        if (operator2 == '(' || operator2 == ')')
            return false;
        else if(operator2 == '*' && operator1 == '/')
            return false;
        else if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-'))
            return false;
        else if (operator1 == '^')
            return false;
        else
            return true;
    }
}
