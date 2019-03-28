package ru.dm_ushakov.mycalculator.lang.func;

import java.math.BigDecimal;

import ru.dm_ushakov.mycalculator.lang.OperationContext;

public class AndFunc extends AbstractFunction {
	@Override
	public BigDecimal evalute(BigDecimal[] args, OperationContext context) {
		if(args.length<2)throw new IllegalArgumentException("and function expects at least 2 arguments");
		
		boolean trueCondition = true;
		
		for(BigDecimal arg:args) {
			if(arg.equals(BigDecimal.ZERO)) {
				trueCondition = false;
				break;
			}
		}
		
		return trueCondition ? BigDecimal.ONE : BigDecimal.ZERO;
	}

}
