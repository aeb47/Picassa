package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;
import model.util.ColorCombinations;

public class Average extends ParenExpression {

	public Average(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	private Average() {
		super();
	}

	public String getExpressionName() {
		return "average";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	public int numberOfParameters() {
		return -1;
	}
	
	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Average(command, expressions);
	}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Average());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() < 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Exprected 1 or More");
		}
		return average(list, map);
	}

	public RGBColor average(List<Expression> expressions,
			Map<String, Expression> map) {
		RGBColor average = expressions.get(0).evaluate(map);
		for (int i = 1; i < expressions.size(); i++) {
			average = ColorCombinations.add(average, expressions.get(i)
					.evaluate(map));
		}
		average = ColorCombinations.divide(average,
				new RGBColor(expressions.size()));
		return average;
	}
}
