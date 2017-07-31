package com.mathandcs.latte.ast;

import com.mathandcs.latte.tokens.Token;

public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token t) {
        super(t);
    }

    public int value() {
        return token().getNumber();
    }
}
