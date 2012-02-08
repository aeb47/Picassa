package model.expression;

import java.util.List;
import java.util.Map;
import java.util.Random;
import model.Expression;
import model.ExpressionFactory;
import model.ParenExpression;
import model.RGBColor;

public class Randoms extends ParenExpression {
	private Randoms() {
		super();
	}
	public Randoms(String command, 	 List<Expression> expressions) {
		super(command, expressions);
	}

	public String getExpressionName() {
		return "random";
	}

	public String getExpressionAlternateName() {
		return null;
	}
	
	public int numberOfParameters() {
		return 0;
	}
	
	public ParenExpression createCorrectExpression(String command,
			List<Expression> expressions) {
		return new Randoms(command, expressions);
	}
	
	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new Randoms());
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		Random generator = new Random();
		RGBColor red = new RGBColor(generator.nextDouble());
		RGBColor green = new RGBColor(generator.nextDouble());
		RGBColor blue = new RGBColor(generator.nextDouble());
		return random(red, green, blue);
	}

	public RGBColor random(RGBColor red, RGBColor green, RGBColor blue) {
		return new RGBColor(red.getRed(), green.getGreen(), blue.getBlue());
	}
}
