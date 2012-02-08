package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;
import model.util.ColorCombinations;

public class Product extends ParenExpression {
	private Product() {
		super();
	}

	public Product(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	public String getExpressionName() {
		return "product";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	public int numberOfParameters() {
		return -1;
	}
	
	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Product(command, expressions);
	}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Product());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() < 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Expected 1 or More");
		}
		return prod(list, map);
	}

	public RGBColor prod(List<Expression> expressions,
			Map<String, Expression> map) {
		RGBColor product = expressions.get(0).evaluate(map);
		for (int i = 1; i < expressions.size(); i++) {
			product = ColorCombinations.multiply(product, expressions.get(i)
					.evaluate(map));
		}
		return product;
	}
}
