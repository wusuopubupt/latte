package com.mathandcs.latte.ast;

import com.mathandcs.latte.env.Environment;
import lombok.Getter;

import java.util.List;

public class WhileStatement extends ASTList {

    @Getter
    private ASTree condition;
    @Getter
    private ASTree body;

    public WhileStatement(List<ASTree> child) {
        super(child);
        condition = child(0);
        body = child(1);
    }

    public String toString() {
        return "(while " + condition + " " + body + ")";
    }

    public Object evaluate(Environment env) {
        Object result = null;
        while (true) {
            if ((Boolean) condition.evaluate(env)) {
                result = body.evaluate(env);
            } else {
                return result;
            }
        }
    }
}
