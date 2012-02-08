package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class Exponentiate extends ParenExpression {
	private Exponentiate() {
		super();
	}

	public Exponentiate(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	public String getExpressionName() {
		return "exp";
	}

	public String getExpressionAlternateName() {
		return "^";
	}

	public int numberOfParameters() {
		return 2;
	}
	
	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Exponentiate(command, expressions);
	}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Exponentiate());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 2) {
			throw new ParserException(
					"Incorrect Number of Arguments: Exprected 2");
		}
		Expression left = list.get(0);
		Expression right = list.get(1);
		return exponent(left.evaluate(map), right.evaluate(map));
	}

	public RGBColor exponent(RGBColor left, RGBColor right) {
		return new RGBColor(Math.pow(left.getRed(), right.getRed()), Math.pow(
				left.getGreen(), right.getGreen()), Math.pow(left.getBlue(),
				right.getBlue()));

	}
}
