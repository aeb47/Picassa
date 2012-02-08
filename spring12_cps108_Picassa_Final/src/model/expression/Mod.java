package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class Mod extends ParenExpression {
	private Mod() {
		super();
	}

	public Mod(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	public String getExpressionName() {
		return "mod";
	}

	public String getExpressionAlternateName() {
		return "%";
	}

	public int numberOfParameters() {
		return 2;
	}
	
	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Mod(command, expressions);
	}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Mod());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 2) {
			throw new ParserException(
					"Incorrect Number of Arguments: Expected 2");
		}
		Expression left = list.get(0);
		Expression right = list.get(1);
		return modulus(left.evaluate(map), right.evaluate(map));
	}

	public RGBColor modulus(RGBColor left, RGBColor right) {
		return new RGBColor(left.getRed() % right.getRed(), left.getGreen()
				% right.getGreen(), left.getBlue() % right.getBlue());

	}
}
