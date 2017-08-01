package com.mathandcs.latte.ast;

import com.mathandcs.latte.env.Environment;
import com.mathandcs.latte.tokens.Token;

public class NumberLiteral extends ASTLeaf {

    private int value;

    public NumberLiteral(Token t) {
        super(t);
        value = token().getNumber();
    }

    public Object evaluate(Environment env) {
        return value;
    }
}
