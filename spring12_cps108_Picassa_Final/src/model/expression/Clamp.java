package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class Clamp extends ParenExpression {

	public Clamp(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	private Clamp() {
		super();
	}

	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Clamp(command, expressions);
	}

	public String getExpressionName() {
		return "clamp";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	public int numberOfParameters() {
		return 1;
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Clamp());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Exprected 1");
		}
		Expression value = list.get(0);
		return clamp(value.evaluate(map));
	}

	public RGBColor clamp(RGBColor value) {
		return new RGBColor(clamp(value.getRed()), clamp(value.getGreen()),
				clamp(value.getBlue()));
	}

	private double clamp(double num) {
		if (num < -1.0)
			return -1.0;
		if (num > 1.0)
			return 1.0;
		else
			return num;
	}
}
