package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.ParserException;
import model.RGBColor;
import model.util.ColorModel;

public class RgbToYCrCb extends ParenExpression {
	private RgbToYCrCb() {
		super();
	}

	public RgbToYCrCb(String command, List<Expression> expressions) {
		super(command, expressions);
	}

	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new RgbToYCrCb(command, expressions);
	}

	public String getExpressionName() {
		return "rgbToYCrCb";
	}

	@Override
	public String getExpressionAlternateName() {
		return null;
	}

	public int numberOfParameters() {
		return 1;
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new RgbToYCrCb());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getSubExpressionList();
		if (list.size() != 1) {
			throw new ParserException(
					"Incorrect Number of Arguments: Expected 1");
		}
		Expression value = list.get(0);
		return rgbToYCrCb(value.evaluate(map));
	}

	public RGBColor rgbToYCrCb(RGBColor only) {
		return ColorModel.rgb2ycrcb(only);
	}
}
