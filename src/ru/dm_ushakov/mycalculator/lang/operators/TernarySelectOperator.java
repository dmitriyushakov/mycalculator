package ru.dm_ushakov.mycalculator.lang.operators;

import java.math.BigDecimal;

import ru.dm_ushakov.mycalculator.lang.OperationContext;

public class TernarySelectOperator extends AbstractOperator {
	private AbstractOperator first;
	private AbstractOperator last;
	
	public TernarySelectOperator(AbstractOperator first,AbstractOperator last) {
		this.first = first;
		this.last = last;

		addChildOperator(first);
		addChildOperator(last);
	}

	@Override
	public BigDecimal evalute(OperationContext context) {
		throw new UnsupportedOperationException("\":\" operator should be used only with \"?\" condition operator");
	}
	
	public BigDecimal evaluteFirst(OperationContext context) {
		return first.evalute(context);
	}
	
	public BigDecimal evaluteLast(OperationContext context) {
		return last.evalute(context);
	}
}
