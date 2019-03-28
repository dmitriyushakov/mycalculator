package ru.dm_ushakov.mycalculator.lang.operators;

import java.math.BigDecimal;
import ru.dm_ushakov.mycalculator.lang.OperationContext;
import ru.dm_ushakov.mycalculator.parser.Token;

public class Number extends AbstractOperator {
	private BigDecimal value;
	
	public Number(Token token) {
		if(!token.getType().equals(Token.TokenType.Number))throw new IllegalArgumentException("Expected number token in constructor");
		value = new BigDecimal(token.getPayload());
	}
	
	public static Number getNegative(Token token) {
		Number num = new Number(token);
		num.value = num.value.negate();
		return num;
	}

	@Override
	public BigDecimal evalute(OperationContext context) {
		return value;
	}
	
	@Override
	public String getDebugPayload() {
		return value.toString();
	}
}
