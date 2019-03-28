package ru.dm_ushakov.mycalculator.lang.operators;

import java.math.BigDecimal;

import ru.dm_ushakov.mycalculator.lang.OperationContext;

public class MinusOperator extends AbstractOperator {
	private AbstractOperator first;
	private AbstractOperator last;
	
	public MinusOperator(AbstractOperator first,AbstractOperator last) {
		this.first = first;
		this.last = last;

		addChildOperator(first);
		addChildOperator(last);
	}
	
	@Override
	public BigDecimal evalute(OperationContext context) {
		BigDecimal firstNum = first.evalute(context);
		BigDecimal lastNum = last.evalute(context);
		
		return firstNum.subtract(lastNum);
	}
}
