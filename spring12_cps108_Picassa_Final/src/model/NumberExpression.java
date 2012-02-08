package model;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberExpression extends Expression {
	private RGBColor myValue;
	private static final Pattern DOUBLE_REGEX = Pattern
			.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");

	private NumberExpression() {

	}

	public NumberExpression(RGBColor number) {
		myValue = number;
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		return myValue;
	}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new NumberExpression());
	}

	public boolean isThisKindOfExpression(String parseableString, Parser parser) {
		Matcher doubleMatcher = DOUBLE_REGEX.matcher(parseableString
				.substring(parser.getParserCurrentPosition()));
		return doubleMatcher.lookingAt();
	}

	public Expression parseExpression(String parseableString, Parser parser) {
		Matcher doubleMatcher = DOUBLE_REGEX.matcher(parseableString);
		doubleMatcher.find(parser.getParserCurrentPosition());
		String numberMatch = parseableString.substring(doubleMatcher.start(),
				doubleMatcher.end());
		parser.setParserCurrentPosition(doubleMatcher.end());
		double value = Double.parseDouble(numberMatch);
		RGBColor gray = new RGBColor(value);
		return new NumberExpression(gray);
	}

	@Override
	public List<Expression> getSubExpressionList() {
		return null;
	}
}
