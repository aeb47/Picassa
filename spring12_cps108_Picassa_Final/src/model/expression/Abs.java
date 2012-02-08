package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class Abs extends ParenExpression {
	public Abs(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	private Abs() {
		super();
	}

	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Abs(command, expressions);
	}

	public String getExpressionName() {
		return "abs";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	
	public int numberOfParameters() {
		return 1;
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Abs());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Exprected 1");
		}
		Expression value = list.get(0);
		return abs(value.evaluate(map));
	}

	public RGBColor abs(RGBColor value) {
		return new RGBColor(Math.abs(value.getRed()),
				Math.abs(value.getGreen()), Math.abs(value.getBlue()));
	}
}
