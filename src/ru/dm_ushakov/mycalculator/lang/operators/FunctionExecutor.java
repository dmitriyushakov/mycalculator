package ru.dm_ushakov.mycalculator.lang.operators;

import java.math.BigDecimal;
import ru.dm_ushakov.mycalculator.lang.OperationContext;
import ru.dm_ushakov.mycalculator.lang.func.AbstractFunction;

public class FunctionExecutor extends AbstractOperator {
	private String functionName;
	
	public FunctionExecutor(String functionName) {
		this.functionName = functionName;
	}
	
	public String getFunctionName() {
		return functionName;
	}
	
	@Override
	public BigDecimal evalute(OperationContext context) {
		AbstractFunction function = context.getFunction(functionName);
		int childOperatorsCnt=childOperators.size();
		
		BigDecimal args[]=new BigDecimal[childOperatorsCnt];
		
		for(int i=0;i<childOperatorsCnt;i++) {
			args[i] = childOperators.get(i).evalute(context);
		}
		
		return function.evalute(args,context);
	}
	
	@Override
	public String getDebugPayload() {
		return functionName;
	}
}
