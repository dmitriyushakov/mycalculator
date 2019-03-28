package ru.dm_ushakov.mycalculator.lang.func;

import java.math.BigDecimal;
import ru.dm_ushakov.mycalculator.lang.OperationContext;

public class CosFunc extends AbstractFunction {
	@Override
	public BigDecimal evalute(BigDecimal[] args,OperationContext context) {
		if(args.length!=1)throw new IllegalArgumentException("cos function expects 1 argument!");
		return new BigDecimal(Math.cos(args[0].doubleValue()));
	}
}
