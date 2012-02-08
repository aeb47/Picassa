package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;
import model.util.PerlinNoise;

public class PerlinColor extends ParenExpression {
	private PerlinColor() {
		super();
	}

	public PerlinColor(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	public String getExpressionName() {
		return "perlinColor";
	}

	public String getExpressionAlternateName() {
		return getExpressionName();
	}

	public int numberOfParameters() {
		return 2;
	}
	
	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new PerlinColor(command, expressions);
	}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new PerlinColor());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 2) {
			throw new ParserException(
					"Incorrect Number of Arguments: Expected 2");
		}
		Expression left = list.get(0);
		Expression right = list.get(1);
		return perlinColor(left.evaluate(map), right.evaluate(map));
	}

	public RGBColor perlinColor(RGBColor left, RGBColor right) {
		return PerlinNoise.colorNoise(left, right);
	}
}
