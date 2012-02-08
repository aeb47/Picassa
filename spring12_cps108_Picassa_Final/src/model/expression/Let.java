package model.expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;

public class Let extends ParenExpression {

	public Let(String command, List<Expression> expressions) {
		super(command, expressions);

	}

	private Let() {
		super();
	}

	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Let(command, expressions);
	}

	public String getExpressionName() {
		return "let";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	public int numberOfParameters() {
		return 3;
	}
	
	public RGBColor evaluate(Map<String, Expression> map) {
		if (map == null) {
			map = new HashMap<String, Expression>();
		}

		List<Expression> list = getSubExpressionList();
		if (list.size() != 3) {
			throw new ParserException(
					"Incorrect Number of Arguments: Expected 3");
		}

		Expression first = list.get(0);
		Expression second = list.get(1);
		Expression third = list.get(2);
		map.put(first.toString(), second);
		return third.evaluate(map);
	}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Let());
	}
}
