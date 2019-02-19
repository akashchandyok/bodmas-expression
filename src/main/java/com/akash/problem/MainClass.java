package com.akash.problem;

import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        BodmassExpressionEvaluator bodmassExpressionEvaluator = new BodmassExpressionEvaluatorImpl();
        CalculatorOperatorService registerOperators = new CalculatorOperatorService();

        registerOperators.registerExpressionService('+',new AddtionService());

        Scanner scanner = new Scanner(System.in);


        int totalInput  = scanner.nextInt();
        String[] expressions = new String[totalInput];

        scanner.nextLine();
        for(int i = 0 ; i <totalInput ; i++  ){
            expressions[i] = scanner.nextLine();
        }

        for(int k = 0 ; k< totalInput ; k++){
            try{
                bodmassExpressionEvaluator.evaluate(expressions[k]);
            }
            catch (InvalidExpressionException ex){
                System.out.println(ex.getMessage());
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }

        }
    }



}
