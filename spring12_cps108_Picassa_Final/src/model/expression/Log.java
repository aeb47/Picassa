package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class Log extends ParenExpression {
	private Log() {
		super();
	}

	public Log(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Log(command, expressions);
	}

	public String getExpressionName() {
		return "log";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	public int numberOfParameters() {
		return 1;
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Log());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Expected 1");
		}
		Expression value = list.get(0);
		return log(value.evaluate(map));
	}

	public RGBColor log(RGBColor only) {
		return new RGBColor(Math.log(only.getRed()), Math.log(only.getGreen()),
				Math.log(only.getBlue()));
	}
}
