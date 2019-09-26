package com.redsun.expression;

import org.springframework.expression.*;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author xuguangrong
 * @description
 * @date Created at 22:39 2019/9/26
 */
public class Main {

    public static void main(String[] args) {
//        sample();
        sample1();
    }

    public static void sample1() {
        String express = "xxx + bbb * 3";
        String newExpress = express.replace("xxx", "3").replace("bbb", "4");
        ExpressionParser parser = new SpelExpressionParser();
        double result = parser.parseExpression(newExpress).getValue(Double.class);

        System.out.println(result);

    }
    public static void sample() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("new String('hello world').toUpperCase()");
        String message = exp.getValue(String.class);
        System.out.println(message);
    }

}
