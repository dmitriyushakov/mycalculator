package ru.dm_ushakov.mycalculator.lang.operators;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.io.PrintStream;
import ru.dm_ushakov.mycalculator.lang.OperationContext;

public abstract class AbstractOperator {
	protected List<AbstractOperator> childOperators=new ArrayList<AbstractOperator>();
	
	public void addChildOperator(AbstractOperator operator) {
		childOperators.add(operator);
	}
	
	public int childOperatorsCount() {
		return childOperators.size();
	}
	
	public AbstractOperator getChildOperator(int index) {
		return childOperators.get(index);
	}
	
	public abstract BigDecimal evalute(OperationContext context);
	
	public String getDebugPayload() {
		return null;
	}
	
	private static void printDebugInfo(AbstractOperator operator,int hierarchyIdentation,StringBuilder sb) {
		for(int i=0;i<hierarchyIdentation;i++)sb.append("    ");
		String operatorName=operator.getClass().getSimpleName();
		sb.append(operatorName);
		String debugPayload=operator.getDebugPayload();
		if(debugPayload!=null) {
			sb.append(" (").append(debugPayload).append(")");
		}
		sb.append("\n");
		
		hierarchyIdentation++;
		for(AbstractOperator child:operator.childOperators) {
			printDebugInfo(child,hierarchyIdentation,sb);
		}
	}
	
	public final void printDebugInfo(PrintStream out) {
		StringBuilder sb=new StringBuilder();
		printDebugInfo(this,0,sb);
		out.println(sb.toString());
	}
}
