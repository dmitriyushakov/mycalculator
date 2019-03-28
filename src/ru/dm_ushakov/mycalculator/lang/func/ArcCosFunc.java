package ru.dm_ushakov.mycalculator.lang.func;

import java.math.BigDecimal;

import ru.dm_ushakov.mycalculator.lang.OperationContext;

public class ArcCosFunc extends AbstractFunction {
	@Override
	public BigDecimal evalute(BigDecimal[] args, OperationContext context) {
		if(args.length!=1)throw new IllegalArgumentException("acos function expects 1 argument!");
		return new BigDecimal(Math.acos(args[0].doubleValue()));
	}
}
