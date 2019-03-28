package ru.dm_ushakov.mycalculator.lang.operators;

import java.math.BigDecimal;

import ru.dm_ushakov.mycalculator.lang.OperationContext;

public class TernaryConditionOperator extends AbstractOperator {
	private AbstractOperator first;
	private TernarySelectOperator last;
	
	public TernaryConditionOperator(AbstractOperator first,AbstractOperator last) {
		if(!(last instanceof TernarySelectOperator))throw new UnsupportedOperationException("\"?\" ternary operator should be used only with \":\" selection operator.");
		
		this.first = first;
		this.last = (TernarySelectOperator)last;
		
		addChildOperator(first);
		addChildOperator(last);
	}

	@Override
	public BigDecimal evalute(OperationContext context) {
		return (!first.evalute(context).equals(BigDecimal.ZERO)) ? last.evaluteFirst(context) : last.evaluteLast(context);
	}
}
