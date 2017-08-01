package com.mathandcs.latte.ast;

import com.mathandcs.latte.env.Environment;
import com.mathandcs.latte.exception.LatteException;
import lombok.Getter;

import java.util.List;

public class Fun extends ASTList {

    @Getter
    private ParameterList parameters;
    @Getter
    private BlockStatement body;

    public Fun(List<ASTree> c) {
        super(c);
        if (numChildren() < 2) {
            throw new LatteException("Function need 2 children, current is: " + numChildren());
        }
        parameters = (ParameterList) c.get(0);
        body = (BlockStatement) c.get(1);
    }

    public String toString() {
        return "(fun " + parameters + " " + body + ")";
    }

    public Object evaluate(Environment env) {
        return null;
    }
}
