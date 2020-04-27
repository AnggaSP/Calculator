package com.example.calculator.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

public class Calculator {

    public static String calculate(String calculation) {
        String[] calculationArray = Helper.convertToPostfix(calculation);
        Stack<BigDecimal> operands = new Stack<>();
        BigDecimal left;
        BigDecimal right;

        for (String string : calculationArray) {
            if (string.trim().equals("")) {
                continue;
            }

            switch (string) {
                case "+":
                case "–":
                case "x":
                case "÷":
                    try {
                        right = new BigDecimal(operands.pop().toString(), MathContext.DECIMAL64);
                    } catch (ArithmeticException | NumberFormatException e) {
                        right = BigDecimal.valueOf(0);
                    }
                    try {
                        left = new BigDecimal(operands.pop().toString(), MathContext.DECIMAL64);
                    } catch (ArithmeticException | NumberFormatException e) {
                        left = BigDecimal.valueOf(0);
                    }

                    BigDecimal value = BigDecimal.valueOf(0);
                    switch (string) {
                        case "+":
                            value = left.add(right);
                            break;
                        case "–":
                            value = left.subtract(right);
                            break;
                        case "x":
                            value = left.multiply(right);
                            break;
                        case "÷":
                            value = left.divide(right, MathContext.DECIMAL64);
                            break;
                        default:
                            break;
                    }
                    operands.push(value);
                    break;
                default:
                    if (!string.equals("."))
                        operands.push(new BigDecimal(string));
                    break;
            }
        }
        return operands.pop().stripTrailingZeros().toPlainString();
    }
}
