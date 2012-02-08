package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class Sin extends ParenExpression {
	private Sin() {
		super();
	}

	public Sin(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Sin(command, expressions);
	}

	public String getExpressionName() {
		return "sin";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	public int numberOfParameters() {
		return 1;
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Sin());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Expected 1");
		}
		Expression value = list.get(0);
		return sin(value.evaluate(map));
	}

	public RGBColor sin(RGBColor only) {
		return new RGBColor(Math.sin(only.getRed()), Math.sin(only.getGreen()),
				Math.sin(only.getBlue()));
	}
}
