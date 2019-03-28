package ru.dm_ushakov.mycalculator.lang;

import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import ru.dm_ushakov.mycalculator.lang.func.AbstractFunction;

public class OperationContext {
	private boolean astDebugEnabled=false;
	private Map<String,BigDecimal> variableMap;
	private Map<String,AbstractFunction> functionsMap;
	
	public OperationContext() {
		variableMap = new HashMap<String,BigDecimal>();
		functionsMap = new HashMap<String,AbstractFunction>();
	}
	
	private OperationContext(Map<String,BigDecimal> variableMap,Map<String,AbstractFunction> functionsMap,boolean astDebugEnabled) {
		this.variableMap = new HashMap<String,BigDecimal>(variableMap);
		this.functionsMap = new HashMap<String,AbstractFunction>(functionsMap);
		this.astDebugEnabled = astDebugEnabled;
	}
	
	public BigDecimal getVariableValue(String variableName) {
		if(!variableMap.containsKey(variableName))throw new IllegalStateException("Variable with name \""+variableName+"\" is not assigned.");
		return variableMap.get(variableName);
	}
	
	public void assignVariableValue(String variableName,BigDecimal val) {
		variableMap.put(variableName, val);
	}
	
	public AbstractFunction getFunction(String functionName) {
		if(!functionsMap.containsKey(functionName))throw new IllegalStateException("Function with name \""+functionName+"\" is not assigned.");
		return functionsMap.get(functionName);
	}
	
	public void assignFunction(String functionName,AbstractFunction function) {
		functionsMap.put(functionName,function);
	}
	
	public boolean isAstDebugEnabled() {
		return astDebugEnabled;
	}
	
	public void enableAstDebug(boolean enabled) {
		astDebugEnabled = enabled;
	}
	
	public void enableAstDebug() {
		enableAstDebug(true);
	}
	
	@Override
	public Object clone() {
		return new OperationContext(variableMap,functionsMap,astDebugEnabled);
	}
}
