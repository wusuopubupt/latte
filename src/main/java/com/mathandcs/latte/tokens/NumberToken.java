package com.mathandcs.latte.tokens;

public class NumberToken extends Token {
	private int value;

	public NumberToken(int line, int value) {
		super(line);
		this.value = value;	
	}

	public boolean isNumber() {
		return true;	
	}

	public String getText() {
		return Integer.toString(value);	
	}

	public int getNumber() {
		return value;	
	}
}
