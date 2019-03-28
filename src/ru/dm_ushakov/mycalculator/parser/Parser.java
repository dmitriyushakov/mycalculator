package ru.dm_ushakov.mycalculator.parser;

import ru.dm_ushakov.mycalculator.lang.operators.Number;
import ru.dm_ushakov.mycalculator.lang.operators.*;
import ru.dm_ushakov.mycalculator.parser.Token.TokenType;

public final class Parser {
	private Parser() {
		
	}

	public static AbstractOperator parseDefaultClause(Tokenizer tokens) throws ParserException {
			return parseDefaultClause(tokens,null,null,true);
	}
	
	private static AbstractOperator produceArythmeticOperator(AbstractOperator leftOperand,Token operationToken,AbstractOperator rightOperand) {
		if(leftOperand!=null && operationToken!=null && rightOperand!=null) {
			switch(operationToken.getType()) {
				case PlusSign:return new PlusOperator(leftOperand,rightOperand);
				case MinusSign:return new MinusOperator(leftOperand,rightOperand);
				case MultiplySign:return new MultiplyOperator(leftOperand,rightOperand);
				case DivideSign:return new DivideOperator(leftOperand,rightOperand);
				case AssigmentSign:return new AssignOperator(leftOperand,rightOperand);
				case TernaryConditionSign:return new TernaryConditionOperator(leftOperand,rightOperand);
				case TernarySelectionSign:return new TernarySelectOperator(leftOperand,rightOperand);
				case Equal:return new ComparationOperator(operationToken,leftOperand,rightOperand);
				case Larger:return new ComparationOperator(operationToken,leftOperand,rightOperand);
				case Lower:return new ComparationOperator(operationToken,leftOperand,rightOperand);
				case LargerOrEqual:return new ComparationOperator(operationToken,leftOperand,rightOperand);
				case LowerOrEqual:return new ComparationOperator(operationToken,leftOperand,rightOperand);
			}
		}
		
		if(rightOperand!=null) {
			return rightOperand;
		}else return leftOperand;
	}
	
	private static AbstractOperator parseDefaultClause(Tokenizer tokens,AbstractOperator leftOperand,Token operationToken,boolean topLevel) throws ParserException {
		Token currentToken = null;
		AbstractOperator rightOperand = null;
		
		while(true) {
			currentToken = tokens.next();
			TokenType currentTokenType = currentToken==null ? null : currentToken.getType();
			
			if(currentToken == null || currentTokenType.equals(TokenType.CommaSign)) {
				break;
			} else if(currentTokenType.equals(TokenType.ClosingParenthesis)) {
				if(topLevel) {
					throw new ParserException("Unexpected ')' symbol.");
				} else {
					break;
				}
			} else if(currentTokenType.equals(TokenType.Number)) {
				rightOperand = new Number(currentToken);
			} else if(currentTokenType.equals(TokenType.Name)) {
				rightOperand = parseNameClause(tokens);
			} else if(currentTokenType.arythmeticOperator()) {
				if(currentTokenType.equals(TokenType.MinusSign) && (rightOperand==null)) {
					currentToken = tokens.next();
					rightOperand = Number.getNegative(currentToken);
				} else {
					leftOperand = rightOperand;
					rightOperand = null;
					operationToken = currentToken;
				}
			} else if(currentTokenType.equals(TokenType.OpeningParenthesis)) {
				rightOperand = parseDefaultClause(tokens,null,null,false);
			}
			
			boolean wasMorePriority=false;
			boolean exitOnClosingParenthesis=false;
			do {
				wasMorePriority=false;
				Token nextToken = tokens.next();
				
				if(
						nextToken!=null &&
						operationToken!=null &&
						rightOperand!=null &&
						nextToken.getType().arythmeticOperator()) {
					if(nextToken.getType().getPriority() > operationToken.getType().getPriority()) {
						rightOperand = parseDefaultClause(tokens,rightOperand,nextToken,false);
						wasMorePriority=true;
						if(tokens.current()!=null&&tokens.current().getType().equals(TokenType.ClosingParenthesis)) {
							exitOnClosingParenthesis=true;
							break;
						}
					} else if(nextToken.getType().getPriority() < operationToken.getType().getPriority() && !topLevel) {
						tokens.backward();
						break;
					} else {
						tokens.backward();
					}
				}else {
					tokens.backward();
				}
			}while(wasMorePriority);
			if(exitOnClosingParenthesis)break;
			
			if(leftOperand!=null && operationToken!=null && rightOperand!=null) {
				rightOperand = produceArythmeticOperator(leftOperand,operationToken,rightOperand);
				operationToken = null;
				leftOperand = null;
			}
		}
		
		return produceArythmeticOperator(leftOperand,operationToken,rightOperand);
	}
	
	private static AbstractOperator parseNameClause(Tokenizer tokens) throws ParserException {
		Token nameToken = tokens.current();
		String name = nameToken.getPayload();
		Token parenthesis = tokens.next();
		
		if(parenthesis!=null && parenthesis.getType().equals(TokenType.OpeningParenthesis)) {
			FunctionExecutor func = new FunctionExecutor(name);
			
			while(true) {
				Token nextToken = tokens.next();
				
				if(nextToken==null) {
					throw new ParserException("Unexpected end of expression!");
				} else if(nextToken.getType().equals(TokenType.ClosingParenthesis)) {
					break;
				} else if (nextToken.getType().equals(TokenType.CommaSign)) {
					continue;
				} else {
					tokens.backward();
					func.addChildOperator(parseDefaultClause(tokens,null,null,false));
					tokens.backward();
				}
			}
			
			return func;
		}else {
			tokens.backward();
			return new VariableExtractor(nameToken);
		}
	}
	
	private static AbstractOperator parseParenthesisClause(Tokenizer tokens) throws ParserException {
		AbstractOperator operator = parseDefaultClause(tokens);
		
		Token finalToken = tokens.current();
		if(finalToken==null || !finalToken.getType().equals(TokenType.ClosingParenthesis)) {
			throw new ParserException("Unexpected end of expression!");
		}
		
		return new Parenthesis(operator);
	}
}
