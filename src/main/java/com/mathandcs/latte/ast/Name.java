package com.mathandcs.latte.ast;

import com.mathandcs.latte.tokens.Token;

public class Name extends ASTLeaf {
    public Name(Token t) {
        super(t);
    }

    public String name() {
        return token().getText();
    }
}
