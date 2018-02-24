package com.mathandcs.latte.ast;

import com.mathandcs.latte.env.Environment;
import com.mathandcs.latte.exception.LatteException;
import com.mathandcs.latte.tokens.Token;

public class Variable extends ASTLeaf {
    public Variable(Token t) {
        super(t);
    }

    public String value() {
        return token().getText();
    }

    public Object evaluate(Environment env) {
        Object value = env.get(value());
        if (null == value) {
            throw new LatteException("undefined variable: " + value());
        }
        return value;
    }
}
