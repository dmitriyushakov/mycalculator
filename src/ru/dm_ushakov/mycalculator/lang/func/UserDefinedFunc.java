package ru.dm_ushakov.mycalculator.lang.func;

import java.math.BigDecimal;
import ru.dm_ushakov.mycalculator.lang.OperationContext;
import ru.dm_ushakov.mycalculator.lang.operators.AbstractOperator;

public class UserDefinedFunc extends AbstractFunction {
	private String[] argNames;
	private AbstractOperator operation;
	
	public UserDefinedFunc(String[] argNames,AbstractOperator operation) {
		this.argNames = argNames;
		this.operation = operation;
	}
	
	@Override
	public BigDecimal evalute(BigDecimal[] args, OperationContext context) {
		OperationContext newContext = (OperationContext)context.clone();
		
		for(int i=0;i<Math.min(argNames.length, args.length);i++) {
			newContext.assignVariableValue(argNames[i], args[i]);
		}
		
		return operation.evalute(newContext);
	}

}
