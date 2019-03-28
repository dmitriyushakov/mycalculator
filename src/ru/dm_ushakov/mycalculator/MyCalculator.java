package ru.dm_ushakov.mycalculator;

import ru.dm_ushakov.mycalculator.lang.OperationContext;
import ru.dm_ushakov.mycalculator.lang.func.*;
import ru.dm_ushakov.mycalculator.lang.operators.AbstractOperator;
import ru.dm_ushakov.mycalculator.parser.Parser;
import ru.dm_ushakov.mycalculator.parser.Tokenizer;
import ru.dm_ushakov.mycalculator.parser.ParserException;

import java.math.BigDecimal;
import java.util.Scanner;

public class MyCalculator {
	public static void main(String[] args) {
		OperationContext context = new OperationContext();
		context.assignFunction("max", new MaxFunc());
		context.assignFunction("min", new MinFunc());
		context.assignFunction("sin", new SinFunc());
		context.assignFunction("cos", new CosFunc());
		context.assignFunction("tan", new TanFunc());
		context.assignFunction("asin", new ArcSinFunc());
		context.assignFunction("acos", new ArcCosFunc());
		context.assignFunction("atan", new ArcTanFunc());
		context.assignFunction("log", new LogFunc());
		context.assignFunction("log10", new Log10Func());
		context.assignFunction("abs", new AbsFunc());
		context.assignFunction("sqrt", new SqrtFunc());
		context.assignFunction("enableAstDebug", new EnableAstDebugFunc());
		context.assignFunction("and", new AndFunc());
		context.assignFunction("or", new OrFunc());
		context.assignFunction("not", new NotFunc());
		
		context.assignVariableValue("pi", new BigDecimal(Math.PI));
		context.assignVariableValue("e", new BigDecimal(Math.E));
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.print(" > ");
			String expression = sc.nextLine();
			
			try {
				Tokenizer tokenizer = new Tokenizer(expression);
				AbstractOperator operator = Parser.parseDefaultClause(tokenizer);
				
				if(context.isAstDebugEnabled()) {
					operator.printDebugInfo(System.out);
				} else {
					System.out.println(operator.evalute(context));
				}
			}catch(ParserException ex) {
				System.err.println("Error caused at expression parsing!");
				ex.printStackTrace();
			}catch(Exception ex) {
				System.err.println("Error caused at expression execution!");
				ex.printStackTrace();
			}
		}
	}
}
