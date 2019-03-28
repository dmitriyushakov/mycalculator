package ru.dm_ushakov.mycalculator.lang.func;

import java.math.BigDecimal;
import ru.dm_ushakov.mycalculator.lang.OperationContext;

public class EnableAstDebugFunc extends AbstractFunction {
	@Override
	public BigDecimal evalute(BigDecimal[] args, OperationContext context) {
		boolean enable = true;
		
		if(args.length > 0) {
			enable = !args[0].equals(BigDecimal.ZERO);
		}
		
		context.enableAstDebug(enable);
		
		return enable ? BigDecimal.ONE : BigDecimal.ZERO;
	}
}
