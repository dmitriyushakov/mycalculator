package ru.dm_ushakov.mycalculator.lang.operators;

import java.math.BigDecimal;
import ru.dm_ushakov.mycalculator.lang.OperationContext;
import ru.dm_ushakov.mycalculator.parser.Token;

public class ComparationOperator extends AbstractOperator {
	private Token token;
	private AbstractOperator first;
	private AbstractOperator last;
	
	public ComparationOperator(Token token,AbstractOperator first,AbstractOperator last) {
		this.token = token;
		this.first = first;
		this.last = last;

		addChildOperator(first);
		addChildOperator(last);
	}

	@Override
	public BigDecimal evalute(OperationContext context) {
		BigDecimal firstNum = first.evalute(context);
		BigDecimal lastNum = last.evalute(context);
		
		boolean result=false;
		switch(token.getType()) {
			case Equal:result=(firstNum.equals(lastNum));break;
			case Lower:result=(firstNum.compareTo(lastNum)<0);break;
			case Larger:result=(firstNum.compareTo(lastNum)>0);break;
			case LowerOrEqual:result=(firstNum.compareTo(lastNum)<=0);break;
			case LargerOrEqual:result=(firstNum.compareTo(lastNum)>=0);break;
		}
		
		return result ? BigDecimal.ONE : BigDecimal.ZERO;
	}
	
	@Override
	public String getDebugPayload() {
		return token.getPayload();
	}
}
