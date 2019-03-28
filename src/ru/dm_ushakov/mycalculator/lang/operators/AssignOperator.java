package ru.dm_ushakov.mycalculator.lang.operators;

import java.math.BigDecimal;

import ru.dm_ushakov.mycalculator.lang.OperationContext;
import ru.dm_ushakov.mycalculator.lang.func.UserDefinedFunc;

public class AssignOperator extends AbstractOperator {
	private AbstractOperator first;
	private AbstractOperator last;
	
	public AssignOperator(AbstractOperator first,AbstractOperator last) {
		this.first = first;
		this.last = last;

		addChildOperator(first);
		addChildOperator(last);
	}
	
	@Override
	public BigDecimal evalute(OperationContext context) {
		if(first instanceof VariableExtractor) {
			String variableName = ((VariableExtractor)first).getVariableName();
			BigDecimal variableValue = last.evalute(context);
			context.assignVariableValue(variableName, variableValue);
			
			return variableValue;
		} else if(first instanceof FunctionExecutor) {
			String functionName = ((FunctionExecutor)first).getFunctionName();
			String argNames[] = new String[first.childOperators.size()];
			int i=0;
			
			for(AbstractOperator child:first.childOperators) {
				if(!(child instanceof VariableExtractor))throw new IllegalStateException("Variable name expected at function definition argument!");
				String argName = ((VariableExtractor)child).getVariableName();
				argNames[i++] = argName;
			}
			
			context.assignFunction(functionName, new UserDefinedFunc(argNames,last));
			
			return BigDecimal.ZERO;
		} else {
			throw new IllegalStateException("Variable name or function definition at left operand expected!");
		}
	}
}