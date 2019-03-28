package ru.dm_ushakov.mycalculator.parser;

public class TokenTypeIsUnknownException extends ParserException{
	private int symbolNum;
	
	public TokenTypeIsUnknownException(int symbolNum) {
		super("Token type is unknown at " + symbolNum);
		
		this.symbolNum = symbolNum;
	}
	
	public int getSymbolNum() {
		return symbolNum;
	}
}
