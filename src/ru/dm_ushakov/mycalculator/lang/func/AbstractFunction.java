package ru.dm_ushakov.mycalculator.lang.func;

import java.math.BigDecimal;
import ru.dm_ushakov.mycalculator.lang.OperationContext;

public abstract class AbstractFunction {
	public abstract BigDecimal evalute(BigDecimal[] args,OperationContext context);
}
