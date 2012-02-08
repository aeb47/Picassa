package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class Negate extends ParenExpression {
	private Negate() {
		super();
	}

	public Negate(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Negate(command, expressions);
	}

	public String getExpressionName() {
		return "neg";
	}

	public String getExpressionAlternateName() {
		return "!";
	}

	public int numberOfParameters() {
		return 1;
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Negate());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Expected 1");
		}
		Expression value = list.get(0);
		return negate(value.evaluate(map));
	}

	public RGBColor negate(RGBColor only) {
		return new RGBColor(1 - only.getRed(), 1 - only.getGreen(),
				1 - only.getBlue());
	}
}
