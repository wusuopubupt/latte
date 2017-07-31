package com.mathandcs.latte;

import org.junit.Test;
import com.mathandcs.latte.gui.CodeDialog;

/**
 * Created by dashwang on 7/31/17.
 */
public class LexerTest {

    @Test
    public void testRead() throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        for (Token t; (t = l.read()) != Token.EOF; )
            System.out.println("=> " + t.getText());
    }
}

