package com.mathandcs.latte.tokens;

public class StringToken extends Token {
	private String literal;

	public StringToken(int line, String str) {
		super(line);	
		this.literal = str;
	}

	public boolean isString() {
		return true;	
	}

	public String getText() {
		return literal;	
	}

}
