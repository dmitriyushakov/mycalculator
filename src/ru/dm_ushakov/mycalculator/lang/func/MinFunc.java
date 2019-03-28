package ru.dm_ushakov.mycalculator.lang.func;

import java.math.BigDecimal;
import ru.dm_ushakov.mycalculator.lang.OperationContext;

public class MinFunc extends AbstractFunction {
	@Override
	public BigDecimal evalute(BigDecimal[] args,OperationContext context) {
		if(args.length<2)throw new IllegalArgumentException("min function expects at least 2 arguments!");

		BigDecimal minValue = args[0];
		
		for(int i=1;i<args.length;i++) {
			BigDecimal curValue = args[i];
			if(curValue.compareTo(minValue)<0)minValue=curValue;
		}
		
		return minValue;
	}
}