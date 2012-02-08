package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;
import model.util.ColorCombinations;

public class Sum extends ParenExpression {
	private Sum() {
		super();
	}

	public Sum(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	public String getExpressionName() {
		return "sum";
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
		return new Sum(command, expressions);
	}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Sum());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() < 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Expected 1 or More");
		}
		return sum(list, map);
	}

	public RGBColor sum(List<Expression> expressions,
			Map<String, Expression> map) {
		RGBColor sum = expressions.get(0).evaluate(map);
		for (int i = 1; i < expressions.size(); i++) {
			sum = ColorCombinations.add(sum, expressions.get(i).evaluate(map));
		}
		return sum;
	}
}
