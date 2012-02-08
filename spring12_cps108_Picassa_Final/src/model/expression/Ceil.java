package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class Ceil extends ParenExpression {

	public Ceil(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	private Ceil() {
		super();
	}

	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Ceil(command, expressions);
	}

	public String getExpressionName() {
		return "ceil";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	
	public int numberOfParameters() {
		return 1;
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Ceil());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Exprected 1");
		}
		Expression value = list.get(0);
		return ceil(value.evaluate(map));
	}

	public RGBColor ceil(RGBColor only) {
		return new RGBColor(Math.ceil(only.getRed()),
				Math.ceil(only.getGreen()), Math.ceil(only.getBlue()));
	}

}
