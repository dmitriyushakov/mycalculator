package ru.dm_ushakov.mycalculator.parser;

public class InvalidTokenTransitionException extends ParserException {
	private Token.TokenType from;
	private Token.TokenType to;
	private int symbolNum;
	
	public InvalidTokenTransitionException(Token.TokenType from,Token.TokenType to,int symbolNum) {
		super("Unallowed token transition from " + from + " to " + to + "at "+ symbolNum + " char.");
		
		this.from = from;
		this.to = to;
		this.symbolNum = symbolNum;
	}
	
	public Token.TokenType getFromType(){
		return from;
	}
	
	public Token.TokenType getToType(){
		return to;
	}
	
	public int getSymbolNum() {
		return symbolNum;
	}
}
