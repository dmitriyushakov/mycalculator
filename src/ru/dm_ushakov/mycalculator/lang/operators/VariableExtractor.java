package ru.dm_ushakov.mycalculator.lang.operators;

import java.math.BigDecimal;

import ru.dm_ushakov.mycalculator.lang.OperationContext;
import ru.dm_ushakov.mycalculator.parser.Token;

public class VariableExtractor extends AbstractOperator {
	private String variableName;
	
	public VariableExtractor(Token token) {
		if(!token.getType().equals(Token.TokenType.Name))throw new IllegalArgumentException("Expected name token in constructor");
		variableName = token.getPayload();
	}
	
	public String getVariableName() {
		return variableName;
	}

	@Override
	public BigDecimal evalute(OperationContext context) {
		return context.getVariableValue(variableName);
	}
	
	@Override
	public String getDebugPayload() {
		return variableName;
	}
}
