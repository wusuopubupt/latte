package com.mathandcs.latte.ast;

import com.mathandcs.latte.env.Environment;
import lombok.Getter;

import java.util.List;

public class ClassStatement extends ASTList {

    @Getter
    private String className;
    @Getter
    private String superClassName;
    @Getter
    private boolean hasSuperClass = false;
    @Getter
    private ClassBody classBody;

    public ClassStatement(List<ASTree> c) {
        super(c);
        className = ((ASTLeaf) child(0)).token().getText();
        classBody = (ClassBody) child(numChildren() - 1);
        if (3 == numChildren()) {
            superClassName = ((ASTLeaf) child(1)).token().getText();
            hasSuperClass = true;
        }
    }

    public String toString() {
        String parent = superClassName;
        if (!hasSuperClass) {
            parent = "*";
        }
        return "(class " + className + " " + parent + " " + classBody + ")";
    }

    public Object evaluate(Environment env) {
        return null;
    }
}
