package ru.dm_ushakov.mycalculator.lang.func;

import java.math.BigDecimal;

import ru.dm_ushakov.mycalculator.lang.OperationContext;

public class AbsFunc extends AbstractFunction {
	@Override
	public BigDecimal evalute(BigDecimal[] args,OperationContext context) {
		if(args.length!=1)throw new IllegalArgumentException("abs function expects 1 argument!");
		BigDecimal val=args[0];
		
		if(val.compareTo(BigDecimal.ZERO) >= 0) {
			return val;
		}else {
			return val.negate();
		}
	}
}
