package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class Atan extends ParenExpression {

	public Atan(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	private Atan() {
		super();
	}

	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Atan(command, expressions);
	}

	public String getExpressionName() {
		return "atan";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	public int numberOfParameters() {
		return 1;
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Atan());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Exprected 1");
		}
		Expression value = list.get(0);
		return atan(value.evaluate(map));
	}

	public RGBColor atan(RGBColor value) {
		return new RGBColor(Math.atan(value.getRed()), Math.atan(value
				.getGreen()), Math.atan(value.getBlue()));
	}
}
