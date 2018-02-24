package com.mathandcs.latte;

import com.mathandcs.latte.ast.ASTree;
import com.mathandcs.latte.exception.ParseException;
import com.mathandcs.latte.gui.CodeDialog;
import com.mathandcs.latte.parser.BasicParser;
import com.mathandcs.latte.tokens.Token;
import org.junit.Test;

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
        Lexer l = new Lexer(new CodeDialog());
        BasicParser parser = new BasicParser();
        while (l.peek(0) != Token.EOF) {
            ASTree ast = parser.parse(l);
            System.out.println("=>" + ast.toString());
        }
    }
}

