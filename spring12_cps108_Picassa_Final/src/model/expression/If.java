package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class If extends ParenExpression {
	private If() {
		super();
	}

	public If(String command, List<Expression> expressions) {
		super(command, expressions);

	}

	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new If(command, expressions);
	}

	public String getExpressionName() {
		return "if";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	public int numberOfParameters() {
		return 3;
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new If());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 3) {
			throw new ParserException(
					"Incorrect Number of Arguments: Expected 3");
		}
		Expression left = list.get(0);
		Expression center = list.get(1);
		Expression right = list.get(2);
		return ifthen(left.evaluate(map), center.evaluate(map),
				right.evaluate(map));
	}

	public RGBColor ifthen(RGBColor left, RGBColor middle, RGBColor right) {
		if (left.getRed() > 0) {
			return middle;
		} else {
			return right;
		}
	}
}
