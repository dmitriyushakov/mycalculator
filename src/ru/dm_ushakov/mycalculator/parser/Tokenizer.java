package ru.dm_ushakov.mycalculator.parser;

import java.util.List;
import java.util.ArrayList;
import java.io.OutputStream;
import java.io.PrintStream;

public class Tokenizer {
	private static class AllowedTransition{
		private Token.TokenType from;
		private Token.TokenType to;
		
		public AllowedTransition(Token.TokenType from,Token.TokenType to) {
			this.from=from;
			this.to=to;
		}
		
		public boolean check(Token.TokenType from,Token.TokenType to) {
			return this.from.equals(from) && this.to.equals(to);
		}
	}
	
	private static List<AllowedTransition> allowedTransitionsList = new ArrayList<AllowedTransition>();
	
	static {
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.BeginOfExpression,Token.TokenType.EndOfExpression));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.BeginOfExpression,Token.TokenType.Number));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.BeginOfExpression,Token.TokenType.Name));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.BeginOfExpression,Token.TokenType.OpeningParenthesis));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.BeginOfExpression,Token.TokenType.MinusSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.OpeningParenthesis,Token.TokenType.Number));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.OpeningParenthesis,Token.TokenType.Name));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.OpeningParenthesis,Token.TokenType.ClosingParenthesis));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.OpeningParenthesis,Token.TokenType.OpeningParenthesis));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.OpeningParenthesis,Token.TokenType.MinusSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Number,Token.TokenType.PlusSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Number,Token.TokenType.MinusSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Number,Token.TokenType.MultiplySign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Number,Token.TokenType.DivideSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Number,Token.TokenType.CommaSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Number,Token.TokenType.ClosingParenthesis));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Number,Token.TokenType.EndOfExpression));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Number,Token.TokenType.TernaryConditionSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Number,Token.TokenType.TernarySelectionSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Number,Token.TokenType.UnknownComparationOperator));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Name,Token.TokenType.PlusSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Name,Token.TokenType.MinusSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Name,Token.TokenType.MultiplySign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Name,Token.TokenType.DivideSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Name,Token.TokenType.CommaSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Name,Token.TokenType.UnknownComparationOperator));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Name,Token.TokenType.OpeningParenthesis));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Name,Token.TokenType.ClosingParenthesis));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Name,Token.TokenType.EndOfExpression));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Name,Token.TokenType.TernaryConditionSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.Name,Token.TokenType.TernarySelectionSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.UnknownComparationOperator,Token.TokenType.Number));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.UnknownComparationOperator,Token.TokenType.Name));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.UnknownComparationOperator,Token.TokenType.OpeningParenthesis));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.UnknownComparationOperator,Token.TokenType.MinusSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.PlusSign,Token.TokenType.Number));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.MinusSign,Token.TokenType.Number));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.MultiplySign,Token.TokenType.Number));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.DivideSign,Token.TokenType.Number));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.PlusSign,Token.TokenType.Name));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.MinusSign,Token.TokenType.Name));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.MultiplySign,Token.TokenType.Name));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.DivideSign,Token.TokenType.Name));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.PlusSign,Token.TokenType.OpeningParenthesis));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.MinusSign,Token.TokenType.OpeningParenthesis));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.MultiplySign,Token.TokenType.OpeningParenthesis));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.DivideSign,Token.TokenType.OpeningParenthesis));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.ClosingParenthesis,Token.TokenType.EndOfExpression));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.ClosingParenthesis,Token.TokenType.CommaSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.ClosingParenthesis,Token.TokenType.ClosingParenthesis));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.ClosingParenthesis,Token.TokenType.PlusSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.ClosingParenthesis,Token.TokenType.MinusSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.ClosingParenthesis,Token.TokenType.MultiplySign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.ClosingParenthesis,Token.TokenType.DivideSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.ClosingParenthesis,Token.TokenType.UnknownComparationOperator));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.ClosingParenthesis,Token.TokenType.TernaryConditionSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.ClosingParenthesis,Token.TokenType.TernarySelectionSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.CommaSign,Token.TokenType.OpeningParenthesis));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.CommaSign,Token.TokenType.Number));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.CommaSign,Token.TokenType.Name));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.CommaSign,Token.TokenType.MinusSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.TernaryConditionSign,Token.TokenType.OpeningParenthesis));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.TernaryConditionSign,Token.TokenType.Number));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.TernaryConditionSign,Token.TokenType.Name));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.TernaryConditionSign,Token.TokenType.MinusSign));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.TernarySelectionSign,Token.TokenType.OpeningParenthesis));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.TernarySelectionSign,Token.TokenType.Number));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.TernarySelectionSign,Token.TokenType.Name));
		allowedTransitionsList.add(new AllowedTransition(Token.TokenType.TernarySelectionSign,Token.TokenType.MinusSign));
	}
	
	private static boolean validateTransition(Token.TokenType from,Token.TokenType to) {
		if(to.equals(Token.TokenType.SpaceSymbol) || from.equals(Token.TokenType.SpaceSymbol))return true;
		
		for(AllowedTransition trans:allowedTransitionsList) {
			if(trans.check(from, to)) return true;
		}
		
		return false;
	}
	
	private static void validateTransitopnWithException(Token.TokenType from,Token.TokenType to,int signNum) throws InvalidTokenTransitionException{
		if(!validateTransition(from,to))throw new InvalidTokenTransitionException(from,to,signNum);
	}
	
	private int currentTokenIter = 0;
	private List<Token> tokensList = new ArrayList<Token>();
	
	public Tokenizer(String expressionStr) throws ParserException {
		char strBuf[] = expressionStr.toCharArray();
		
		Token.TokenType currentTokenType = Token.TokenType.BeginOfExpression;
		int tokenBegin = 0;
		
		for(int i=0;i<strBuf.length;i++) {
			char symbol=strBuf[i];
			if(!currentTokenType.isAllowedSymbol(symbol)) {
				boolean knownTokenType = false;
				for(Token.TokenType tokenType:Token.TokenType.values()) {
					if(tokenType.isOpeningSymbol(symbol)) {
						validateTransitopnWithException(currentTokenType,tokenType,tokenBegin);
						
						if(!currentTokenType.equals(Token.TokenType.BeginOfExpression) && !currentTokenType.equals(Token.TokenType.SpaceSymbol)) {
							int payloadBufLen = i - tokenBegin;
							char payloadBuf[] = new char[payloadBufLen];
							
							for(int j = 0; j < payloadBufLen; j++) payloadBuf[j] = strBuf[tokenBegin + j];
							tokensList.add(new Token(currentTokenType,new String(payloadBuf),tokenBegin));
						}
						
						currentTokenType=tokenType;
						tokenBegin = i;
						knownTokenType = true;
						break;
					}
				}
				
				if(!knownTokenType) {
					throw new TokenTypeIsUnknownException(i);
				}
			}
		}
		
		validateTransitopnWithException(currentTokenType,Token.TokenType.EndOfExpression,tokenBegin);
		int payloadBufLen = strBuf.length - tokenBegin;
		char payloadBuf[] = new char[payloadBufLen];
		
		for(int i = 0; i < payloadBufLen; i++) payloadBuf[i] = strBuf[tokenBegin + i];
		tokensList.add(new Token(currentTokenType,new String(payloadBuf),tokenBegin));
	}
	
	public void debugPrintTokensList(OutputStream out) {
		debugPrintTokensList(new PrintStream(out));
	}
	
	public void debugPrintTokensList(PrintStream out) {
		for(Token token:tokensList) {
			out.println(token.getType().toString() + ": \"" + token.getPayload() + "\" at " + token.getSymbolNum() + " symbol");
		}
	}
	
	public Token next() {
		if(currentTokenIter == tokensList.size()) {
			currentTokenIter++;
			return null;
		} else if(currentTokenIter >= tokensList.size()) {
			return null;
		} else {
			Token token = tokensList.get(currentTokenIter);
			currentTokenIter++;
			return token;
		}
	}
	
	public Token current() {
		if(currentTokenIter-1 >= tokensList.size() || currentTokenIter <= 0) {
			return null;
		} else {
			Token token = tokensList.get(currentTokenIter-1);
			return token;
		}
	}
	
	public void backward() {
		currentTokenIter--;
	}
}