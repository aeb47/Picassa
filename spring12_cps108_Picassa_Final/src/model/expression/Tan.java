package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class Tan extends ParenExpression {
	private Tan() {
		super();
	}

	public Tan(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Tan(command, expressions);
	}

	public String getExpressionName() {
		return "tan";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	public int numberOfParameters() {
		return 1;
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Tan());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Expected 1");
		}
		Expression value = list.get(0);
		return tan(value.evaluate(map));
	}

	public RGBColor tan(RGBColor only) {
		return new RGBColor(Math.tan(only.getRed()), Math.tan(only.getGreen()),
				Math.tan(only.getBlue()));
	}
}
