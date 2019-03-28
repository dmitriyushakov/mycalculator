package ru.dm_ushakov.mycalculator.lang.func;

import java.math.BigDecimal;
import ru.dm_ushakov.mycalculator.lang.OperationContext;

public class MaxFunc extends AbstractFunction {
	@Override
	public BigDecimal evalute(BigDecimal[] args,OperationContext context) {
		if(args.length<2)throw new IllegalArgumentException("max function expects at least 2 arguments!");

		BigDecimal maxValue = args[0];
		
		for(int i=1;i<args.length;i++) {
			BigDecimal curValue = args[i];
			if(curValue.compareTo(maxValue)>0)maxValue=curValue;
		}
		
		return maxValue;
	}
}
