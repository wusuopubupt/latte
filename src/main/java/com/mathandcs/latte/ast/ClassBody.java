package com.mathandcs.latte.ast;

import com.mathandcs.latte.env.Environment;

import java.util.List;

public class ClassBody extends ASTList {
    public ClassBody(List<ASTree> c) {
        super(c);
    }

    public Object evaluate(Environment env) {
        return null;
    }
}
