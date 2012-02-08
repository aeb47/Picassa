package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class Color extends ParenExpression {

	public Color(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	private Color() {
		super();
	}

	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Color(command, expressions);
	}

	public String getExpressionName() {
		return "color";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	public int numberOfParameters() {
		return 3;
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Color());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 3) {
			throw new ParserException(
					"Incorrect Number of Arguments: Exprected 3");
		}
		Expression left = list.get(0);
		Expression center = list.get(1);
		Expression right = list.get(2);
		return color(left.evaluate(map), center.evaluate(map),
				right.evaluate(map));
	}

	public RGBColor color(RGBColor left, RGBColor middle, RGBColor right) {
		return new RGBColor(left.getRed(), middle.getGreen(), right.getBlue());
	}
}
