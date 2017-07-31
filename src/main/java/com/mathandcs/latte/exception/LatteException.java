package com.mathandcs.latte.exception;

import com.mathandcs.latte.ast.ASTree;

public class LatteException extends RuntimeException {
    public LatteException(String m) {
        super(m);
    }

    public LatteException(String m, ASTree t) {
        super(m + " " + t.location());
    }
}
