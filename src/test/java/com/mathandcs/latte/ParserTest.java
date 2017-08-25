package com.mathandcs.latte;

import com.mathandcs.latte.ast.ASTree;
import com.mathandcs.latte.env.Environment;
import com.mathandcs.latte.exception.ParseException;
import com.mathandcs.latte.gui.CodeDialog;
import com.mathandcs.latte.parser.BasicParser;
import com.mathandcs.latte.tokens.Token;
import org.junit.Test;

/**
 * Created by dashwang on 7/31/17.
 */
public class ParserTest {

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

