package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;
import model.util.ColorModel;

public class YCrCbtoRGB extends ParenExpression {
	private YCrCbtoRGB() {
		super();
	}

	public YCrCbtoRGB(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new YCrCbtoRGB(command, expressions);
	}

	public String getExpressionName() {
		return "yCrCbtoRGB";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	public int numberOfParameters() {
		return 1;
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new YCrCbtoRGB());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Expected 1");
		}
		Expression value = list.get(0);
		return yCrCbtoRGB(value.evaluate(map));
	}

	public RGBColor yCrCbtoRGB(RGBColor only) {
		return ColorModel.ycrcb2rgb(only);
	}
}
