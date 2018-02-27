package com.mathandcs.latte;

import com.mathandcs.latte.ast.ASTree;
import com.mathandcs.latte.env.Environment;
import com.mathandcs.latte.env.impl.BasicEnv;
import com.mathandcs.latte.exception.ParseException;
import com.mathandcs.latte.gui.CodeDialog;
import com.mathandcs.latte.parser.BasicParser;
import com.mathandcs.latte.tokens.Token;
import org.junit.Assert;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dashwang on 7/31/17.
 */
public class ParserTest {

    // if statement:
    /*
    x = -1

    if 2 > 1 {
        x = 1
    } else {
         x = 0
    }
    */

    // while statement:
    /*
    sum = 0
    i = 1
    n = 10

    while i < n {
        sum = sum + i
        i = i + 1
    }

    sum
     */

    @Test
    public void testParse() throws ParseException {
        //System.out.print("Enter something : ");
        // InputStreamReader not working..
        //Reader reader = new BufferedReader(new InputStreamReader(System.in));
        Reader reader = new CodeDialog();
        Lexer l = new Lexer(reader);
        BasicParser parser = new BasicParser();
        Map<Object, Object> envMap = new HashMap<>();
        envMap.put("x", -1);
        Environment env = new BasicEnv(envMap);
        while (l.peek(0) != Token.EOF) {
            ASTree ast = parser.parse(l);
            System.out.println("=>" + ast.toString());
            System.out.println(ast.evaluate(env));
        }
    }

    @Test
    public void testParseString() throws Exception {
        StringBuilder sb = new StringBuilder("x = -1\n")
                .append("if 1 > 2 {\n x = x+1\n} else {\nx = x + 2}\n")
                .append("x");

        String s = sb.toString();
        Reader reader = new StringReader(s);
        Lexer l = new Lexer(reader);
        BasicParser parser = new BasicParser();
        Map<Object, Object> envMap = new HashMap<>();
        envMap.put("x", -1);
        Environment env = new BasicEnv(envMap);
        Object ret = null;
        while (l.peek(0) != Token.EOF) {
            ASTree ast = parser.parse(l);
            System.out.println("=>" + ast.toString());
            System.out.println((ret = ast.evaluate(env)));
        }

        System.out.println("Evaluate result is: " + ret);
        Assert.assertEquals(ret, 1);
    }
}

