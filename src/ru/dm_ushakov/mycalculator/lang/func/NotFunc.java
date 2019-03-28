package ru.dm_ushakov.mycalculator.lang.func;

import java.math.BigDecimal;

import ru.dm_ushakov.mycalculator.lang.OperationContext;

public class NotFunc extends AbstractFunction {
	@Override
	public BigDecimal evalute(BigDecimal[] args, OperationContext context) {
		if(args.length!=1)throw new IllegalArgumentException("not function expects 1 argument");
		return args[0].equals(BigDecimal.ZERO)?BigDecimal.ONE:BigDecimal.ZERO;
	}
}
