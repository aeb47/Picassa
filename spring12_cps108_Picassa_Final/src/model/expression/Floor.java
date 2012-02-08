package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class Floor extends ParenExpression {
	private Floor() {
		super();
	}

	public Floor(String command, List<Expression> expressions) {
		super(command, expressions);

	}

	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Floor(command, expressions);
	}

	public String getExpressionName() {
		return "floor";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	public int numberOfParameters() {
		return 1;
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Floor());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Exprected 1");
		}
		Expression value = list.get(0);
		return floor(value.evaluate(map));
	}

	public RGBColor floor(RGBColor only) {
		return new RGBColor(Math.floor(only.getRed()), Math.floor(only
				.getGreen()), Math.floor(only.getBlue()));
	}
}
