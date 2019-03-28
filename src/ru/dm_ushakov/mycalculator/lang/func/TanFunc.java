package ru.dm_ushakov.mycalculator.lang.func;

import java.math.BigDecimal;

import ru.dm_ushakov.mycalculator.lang.OperationContext;

public class TanFunc extends AbstractFunction {
	@Override
	public BigDecimal evalute(BigDecimal[] args, OperationContext context) {
		if(args.length!=1)throw new IllegalArgumentException("tan function expects 1 argument!");
		return new BigDecimal(Math.tan(args[0].doubleValue()));
	}
}
