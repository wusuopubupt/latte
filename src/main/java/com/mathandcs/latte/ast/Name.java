package com.mathandcs.latte.ast;

import com.mathandcs.latte.env.Environment;
import com.mathandcs.latte.exception.LatteException;
import com.mathandcs.latte.tokens.Token;

public class Name extends ASTLeaf {
    public Name(Token t) {
        super(t);
    }

    public String name() {
        return token().getText();
    }

    public Object evaluate(Environment env) {
        Object value = env.get(name());
        if (null == value) {
            throw new LatteException("undefined variable: " + name());
        }
        return value;
    }
}
