package com.mathandcs.latte.ast;

import com.mathandcs.latte.env.Environment;
import com.mathandcs.latte.tokens.Token;

public class StringLiteral extends ASTLeaf {

    private String value;

    public StringLiteral(Token t) {
        super(t);
        value = token().getText();
    }

    @Override
    public Object evaluate(Environment env) {
        return value;
    }
}
