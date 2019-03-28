package ru.dm_ushakov.mycalculator.lang.operators;

import java.math.BigDecimal;

import ru.dm_ushakov.mycalculator.lang.OperationContext;

public class Parenthesis extends AbstractOperator {
	private AbstractOperator proxy;
	
	public Parenthesis(AbstractOperator proxy) {
		this.proxy = proxy;
		addChildOperator(proxy);
	}

	@Override
	public BigDecimal evalute(OperationContext context) {
		return proxy.evalute(context);
	}

}
