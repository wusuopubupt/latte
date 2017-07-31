package com.mathandcs.latte.ast;

import com.mathandcs.latte.Token;

public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token t) {
        super(t);
    }

    public String value() {
        return token().getText();
    }
}
