package com.mathandcs.latte.ast;

import com.mathandcs.latte.env.Environment;
import com.mathandcs.latte.exception.LatteException;
import lombok.Getter;

import java.util.List;

public class IfStatement extends ASTList {

    @Getter
    private ASTree condition;
    @Getter
    private ASTree thenBlock;
    @Getter
    private ASTree elseBlock;

    public IfStatement(List<ASTree> c) {
        super(c);
        if (numChildren() < 2) {
            throw new LatteException("IfStatement need at last 2 children, current is: " + numChildren());
        }
        ;
        condition = child(0);
        thenBlock = child(1);
        elseBlock = numChildren() > 2 ? child(2) : null;
    }

    public String toString() {
        return "(if " + condition + " " + thenBlock + " else " + elseBlock + ")";
    }

    public Object evaluate(Environment env) {
        Boolean cond = (Boolean) condition.evaluate(env);
        if (cond) {
            return thenBlock.evaluate(env);
        } else {
            return elseBlock.evaluate(env);
        }
    }
}
