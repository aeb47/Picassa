package model;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableExpression extends Expression {
	private static final Pattern VAR_REGEX = Pattern.compile("[xyt]");
	private String myVariable;

	private VariableExpression() {

	}

	public VariableExpression(String var) {
		myVariable = var;
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		if (myVariable != null) {
			if (myVariable.equals("t"))
				return new RGBColor(map.get("t").evaluate(map));
			if (myVariable.equals("x"))
				return new RGBColor(map.get("x").evaluate(map));
			else
				return new RGBColor(map.get("y").evaluate(map));
		} else
			return null;
	}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new VariableExpression());
	}

	public boolean isThisKindOfExpression(String parseableString, Parser parser) {
		Matcher varMatcher = VAR_REGEX.matcher(parseableString.substring(parser
				.getParserCurrentPosition()));
		return varMatcher.lookingAt();
	}

	public Expression parseExpression(String parseableString, Parser parser) {
		Matcher varMatcher = VAR_REGEX.matcher(parseableString);
		varMatcher.find(parser.getParserCurrentPosition());
		String var = varMatcher.group(0);
		parser.setParserCurrentPosition(varMatcher.end());
		return new VariableExpression(var);
	}

	@Override
	public List<Expression> getSubExpressionList() {
		return null;
	}
}
