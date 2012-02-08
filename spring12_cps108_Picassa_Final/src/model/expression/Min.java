package model.expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class Min extends ParenExpression {
	private Min() {
		super();
	}

	public Min(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	public String getExpressionName() {
		return "min";
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
		return new Min(command, expressions);
	}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Min());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() < 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Expected 1 or More");
		}
		return min(list, map);
	}

	public RGBColor min(List<Expression> expressions,
			Map<String, Expression> map) {
		List<RGBColor> colors = new ArrayList<RGBColor>();
		for (Expression expression : expressions) {
			colors.add(expression.evaluate(map));
		}
		Collections.sort(colors);
		return colors.get(0);
	}
}
