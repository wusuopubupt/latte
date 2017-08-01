package com.mathandcs.latte.ast;

import com.mathandcs.latte.env.Environment;

import java.util.List;

public class BlockStatement extends ASTList {
    public BlockStatement(List<ASTree> c) {
        super(c);
    }

    public Object evaluate(Environment env) {
        return null;
    }
}
