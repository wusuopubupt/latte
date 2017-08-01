package com.mathandcs.latte.ast;

import com.mathandcs.latte.env.Environment;
import com.mathandcs.latte.tokens.Token;

public class NullStatement extends ASTLeaf {
    public NullStatement(Token t) {
        super(t);
    }

    public String name() {
        return token().getText();
    }

    public Object evaluate(Environment env) {
        return name();
    }
}
