package com.redsun.expression;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author xuguangrong
 * @description
 * @date Created at 22:39 2019/9/26
 */
public class Main {

    public static void main(String[] args) {
//        sample();
//        sample1();
//        sample2();
        sample3();
    }

    public static void sample() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("new String('hello world').toUpperCase()");
        String message = exp.getValue(String.class);
        System.out.println(message);
    }

    public static void sample1() {
        String express = "xxx + bbb * 3 + ";
        String newExpress = express.replace("xxx", "3").replace("bbb", "4");
        ExpressionParser parser = new SpelExpressionParser();
        double result = parser.parseExpression(newExpress).getValue(Double.class);

        System.out.println(result);

    }

    public static void sample2() {
        // Create and set a calendar
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);

        //  The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        ExpressionParser parser = new SpelExpressionParser();
//        Expression exp = parser.parseExpression("name");
        Expression exp = parser.parseExpression("birthday");
        EvaluationContext context = new StandardEvaluationContext(tesla);

//        String name = (String) exp.getValue(context);
        Date date = (Date) exp.getValue(context);
//        System.out.println(name);
        System.out.println(date);
    }

    public static void sample3() {
        // Create and set a calendar
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);

        //  The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("birthday");

        Date date = (Date) exp.getValue(tesla);
        System.out.println(date);
    }

}
