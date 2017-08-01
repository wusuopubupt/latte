package com.mathandcs.latte.ast;

import com.mathandcs.latte.env.Environment;

import java.util.List;

public class ParameterList extends ASTList {
    public ParameterList(List<ASTree> c) {
        super(c);
    }

    public String name(int i) {
        return ((ASTLeaf) child(i)).token().getText();
    }

    public int size() {
        return numChildren();
    }

    public Object evaluate(Environment env) {
        return null;
    }
}
