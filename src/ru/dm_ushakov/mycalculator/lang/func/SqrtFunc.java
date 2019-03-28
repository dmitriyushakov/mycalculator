package ru.dm_ushakov.mycalculator.lang.func;

import java.math.BigDecimal;

import ru.dm_ushakov.mycalculator.lang.OperationContext;

public class SqrtFunc extends AbstractFunction {
	@Override
	public BigDecimal evalute(BigDecimal[] args, OperationContext context) {
		if(args.length!=1)throw new IllegalArgumentException("sqrt function expects 1 argument!");
		return new BigDecimal(Math.sqrt(args[0].doubleValue()));
	}
}
