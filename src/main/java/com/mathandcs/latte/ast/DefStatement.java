package com.mathandcs.latte.ast;

import com.mathandcs.latte.env.Environment;
import com.mathandcs.latte.exception.LatteException;
import lombok.Getter;

import java.util.List;

public class DefStatement extends ASTList {

    @Getter
    private String name;
    @Getter
    private ParameterList parameters;
    @Getter
    private BlockStatement body;

    public DefStatement(List<ASTree> c) {
        super(c);
        if (numChildren() < 3) {
            throw new LatteException("DefStatement need 3 child, current is: " + numChildren());
        }
        name = ((ASTLeaf) c.get(0)).token().getText();
        parameters = (ParameterList) c.get(1);
        body = (BlockStatement) c.get(2);
    }

    public String toString() {
        return "(def " + name + " " + parameters + " " + body + ")";
    }

    public Object evaluate(Environment env) {
        return null;
    }
}
