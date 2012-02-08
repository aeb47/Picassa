package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssignmentExpression extends Expression {
	private String myVariable;
	private static final Pattern ASSIGNMENT_BEGIN_REGEX = Pattern
			.compile("[a-zA-Z]+");

	public AssignmentExpression(String varName) {
		myVariable = varName;
	}

	private AssignmentExpression() {
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(myVariable);
		return result.toString();
	}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new AssignmentExpression());
	}

	public boolean isThisKindOfExpression(String parseableString, Parser parser) {
		Matcher varMatcher = ASSIGNMENT_BEGIN_REGEX.matcher(parseableString
				.substring(parser.getParserCurrentPosition()));
		return varMatcher.lookingAt();
	}

	public Expression parseExpression(String parseableString, Parser parser) {
		Matcher varMatcher = ASSIGNMENT_BEGIN_REGEX.matcher(parseableString);
		varMatcher.find(parser.getParserCurrentPosition());
		String var = varMatcher.group(0);
		parser.setParserCurrentPosition(varMatcher.end());
		return new AssignmentExpression(var);
	}

	public RGBColor evaluate(Map<String, Expression> map) {
		if (map == null)
			throw new ParserException("Undefined variable: " + myVariable);
		if (map.containsKey(myVariable))
			return map.get(myVariable).evaluate(map);
		else {
			throw new ParserException("Undefined variable: " + myVariable);
		}
	}

	@Override
	public ArrayList<Expression> getSubExpressionList() {
		return null;
	}

}
