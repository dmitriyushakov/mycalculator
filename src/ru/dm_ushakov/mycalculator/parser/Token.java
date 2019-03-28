package ru.dm_ushakov.mycalculator.parser;

public class Token {
	public enum TokenType {
		Number(getCharsRange('0','9') + ".",getCharsRange('0','9')),
		Name(getCharsRange('a','z') + getCharsRange('A','Z') + getCharsRange('0','9'), getCharsRange('a','z')+getCharsRange('A','Z')),
		OpeningParenthesis("","("),
		ClosingParenthesis("",")"),
		PlusSign("","+",1),
		MinusSign("","-",1),
		MultiplySign("","*",2),
		DivideSign("","/",2),
		AssigmentSign("",0),
		TernaryConditionSign("","?",3),
		TernarySelectionSign("",":",4),
		UnknownComparationOperator("=<>"),
		Larger("",5),
		Lower("",5),
		LargerOrEqual("",5),
		LowerOrEqual("",5),
		Equal("",5),
		CommaSign(","),
		SpaceSymbol(" \t\r\n"),
		EndOfExpression(""),
		BeginOfExpression("");
		private char[] charsClass;
		private char[] openingCharsClass;
		private boolean arythmeticOperator = false;
		private int priority = -1;
		
		private static String getCharsRange(char beginChar,char endChar) {
			int rangeLength=1 + (int)(endChar - beginChar);
			char buf[]=new char[rangeLength];
			
			for(int i=0;i < rangeLength;i++) {
				buf[i] = beginChar;
				beginChar++;
			}
			
			return new String(buf);
		}
		
		TokenType(String chars,String openingChars) {
			this.charsClass = chars.toCharArray();
			this.openingCharsClass = openingChars.toCharArray();
		}
		
		TokenType(String chars) {
			this.charsClass = chars.toCharArray();
			this.openingCharsClass = this.charsClass;
		}
		
		TokenType(String chars,int priority) {
			this.charsClass = chars.toCharArray();
			this.openingCharsClass = this.charsClass;
			
			arythmeticOperator = true;
			this.priority = priority;
		}
		
		TokenType(String chars,String openingChars,int priority) {
			this.charsClass = chars.toCharArray();
			this.openingCharsClass = openingChars.toCharArray();
			
			arythmeticOperator = true;
			this.priority = priority;
		}
		
		public boolean isAllowedSymbol(char c) {
			int classLen = charsClass.length;
			for(int i = 0; i < classLen; i++) if(charsClass[i]==c) return true;
			return false;
		}
		
		public boolean isOpeningSymbol(char c) {
			int classLen = openingCharsClass.length;
			for(int i = 0; i < classLen; i++) if(openingCharsClass[i]==c) return true;
			return false;
		}
		
		public boolean arythmeticOperator() {
			return arythmeticOperator;
		}
		
		public int getPriority() throws ParserException {
			if(priority == -1) {
				throw new ParserException("Unallowable to require priority from this token");
			} else {
				return priority;
			}
		}
		
		public static TokenType decodeComparationToken(String str) throws ParserException {
			switch(str) {
				case ">":return TokenType.Larger;
				case "<":return TokenType.Lower;
				case ">=":return TokenType.LargerOrEqual;
				case "<=":return TokenType.LowerOrEqual;
				case "==":return TokenType.Equal;
				case "=":return TokenType.AssigmentSign;
				default:throw new ParserException("Unknown comparation operator");
			}
		}
	}
	
	private TokenType type;
	private String payload;
	private int symbolNum;
	
	public Token(TokenType type,String payload,int symbolNum) throws ParserException {
		if(type.equals(TokenType.UnknownComparationOperator))type=TokenType.decodeComparationToken(payload);
		
		this.type = type;
		this.payload = payload;
		this.symbolNum = symbolNum;
	}
	
	public TokenType getType() {
		return type;
	}
	
	public String getPayload() {
		return payload;
	}
	
	public int getSymbolNum() {
		return symbolNum;
	}
}
