package com.mathandcs.latte.ast;

import com.mathandcs.latte.env.Environment;

import java.util.List;

public class NegativeExpr extends ASTList {
    public NegativeExpr(List<ASTree> c) {
        super(c);
    }

    public ASTree operand() {
        return child(0);
    }

    public String toString() {
        return "-" + operand();
    }

    @Override
    public Object evaluate(Environment env) {
        return -1 * (Integer) operand().evaluate(env);
    }
}
