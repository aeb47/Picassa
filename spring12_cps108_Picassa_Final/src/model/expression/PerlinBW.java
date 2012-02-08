package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;
import model.util.PerlinNoise;

public class PerlinBW extends ParenExpression {
	private PerlinBW() {
		super();
	}

	public PerlinBW(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	public String getExpressionName() {
		return "perlinBW";
	}

	public String getExpressionAlternateName() {
		return "perlinBW";
	}

	public int numberOfParameters() {
		return 2;
	}
	
	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new PerlinBW(command, expressions);
	}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new PerlinBW());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 2) {
			throw new ParserException(
					"Incorrect Number of Arguments: Expected 2");
		}
		Expression left = list.get(0);
		Expression right = list.get(1);
		return perlinBW(left.evaluate(map), right.evaluate(map));
	}

	public RGBColor perlinBW(RGBColor left, RGBColor right) {
		return PerlinNoise.greyNoise(left, right);
	}
}
