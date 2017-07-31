package com.mathandcs.latte.ast;

import com.mathandcs.latte.Token;

public class Name extends ASTLeaf {
    public Name(Token t) {
        super(t);
    }

    public String name() {
        return token().getText();
    }
}
