package com.mathandcs.latte;

import com.mathandcs.latte.exception.ParseException;

public abstract class Token {

    public static final int EOF_MARK = -1;
    public static final String EOL = "\\n";
    public static final Token EOF = new Token(EOF_MARK) {
    };

    private int lineNumber;

    protected Token(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public boolean isIdentifier() {
        return false;
    }

    public boolean isNumber() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public int getNumber() {
        throw new LatteException("not number token");
    }

    public String getText() {
        return "";
    }
}
