package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class Cos extends ParenExpression {

	public Cos(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	private Cos() {
		super();
	}

	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Cos(command, expressions);
	}

	public String getExpressionName() {
		return "cos";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	public int numberOfParameters() {
		return 1;
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Cos());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Exprected 1");
		}
		Expression value = list.get(0);
		return cos(value.evaluate(map));
	}

	public RGBColor cos(RGBColor only) {
		return new RGBColor(Math.cos(only.getRed()), Math.cos(only.getGreen()),
				Math.cos(only.getBlue()));
	}
}
