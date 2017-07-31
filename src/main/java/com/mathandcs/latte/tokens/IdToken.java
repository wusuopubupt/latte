package com.mathandcs.latte.tokens;

public class IdToken extends Token {
	private String text;

	public IdToken(int line, String text) {
		super(line);	
		this.text = text;
	}

	public boolean isIdentifier() {
		return true;	
	}

	public String getText() {
		return text;	
	}
}
