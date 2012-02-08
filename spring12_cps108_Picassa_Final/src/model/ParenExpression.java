package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ParenExpression extends Expression {
	private List<Expression> subExpressionList = new ArrayList<Expression>();
	private static final Pattern EXPRESSION_BEGIN_REGEX = Pattern
			.compile("\\(([a-zA-Z^%/+*-/!]+)");

	protected ParenExpression() {

	}

	public ParenExpression(String command, List<Expression> expressions) {
		subExpressionList = expressions;
	}

	public List<Expression> getSubExpressionList() {
		return this.subExpressionList;
	}

	public abstract String getExpressionName();

	public abstract String getExpressionAlternateName();

	public abstract int numberOfParameters();

	public abstract ParenExpression createCorrectExpression(String command,
			List<Expression> expressions);

	public abstract RGBColor evaluate(Map<String, Expression> map);

	public boolean isThisKindOfExpression(String parseableString, Parser parser) {
		int index = parseableString
				.substring(parser.getParserCurrentPosition()).indexOf(" ");

		if (index == -1) {
			index = parseableString
					.substring(parser.getParserCurrentPosition()).indexOf(")");
			if (parseableString.substring(parser.getParserCurrentPosition())
					.indexOf("(") != 0) {
				return false;
			}
			String commandName = parseableString.substring(
					parser.getParserCurrentPosition() + 1,
					parser.getParserCurrentPosition() + index);
			if (commandName.equals(getExpressionName())) {
				return true;
			}
			return false;
		} else {
			if (parseableString.substring(parser.getParserCurrentPosition())
					.indexOf("(") != 0) {
				return false;
			}
			String commandName = parseableString.substring(
					parser.getParserCurrentPosition() + 1,
					parser.getParserCurrentPosition() + index);

			if (commandName.equals(getExpressionName())
					|| commandName.equals(getExpressionAlternateName())) {
				return true;
			}
			return false;
		}
	}

	public Expression parseExpression(String parseableString, Parser parser) {
		if (!isThisKindOfExpression(parseableString, parser))
			throw new ParserException("Attempt to parse invalid string as "
					+ getExpressionName() + " paren expression");

		Matcher expMatcher = EXPRESSION_BEGIN_REGEX.matcher(parseableString);
		expMatcher.find(parser.getParserCurrentPosition());
		String commandName = expMatcher.group(1);
		parser.setParserCurrentPosition(expMatcher.end());

		List<Expression> expressions = new ArrayList<Expression>();

		if (numberOfParameters() == -1) {
			while (parser.getParserCurrentCharacter() != ')') {
				parser.skipWhiteSpace();
				expressions
						.add(parser.parseExpression(parseableString, parser));
			}
		} else {
			for (int i = 0; i < numberOfParameters(); i++) {
				parser.skipWhiteSpace();
				expressions
						.add(parser.parseExpression(parseableString, parser));
			}
		}
		parser.skipWhiteSpace();
		if (parser.getParserCurrentCharacter() == ')') {
			parser.setParserCurrentPosition(parser.getParserCurrentPosition() + 1);
			return createCorrectExpression(commandName, expressions);
		} else {
			throw new ParserException("Expected close paren, instead found "
					+ parseableString.substring(parser
							.getParserCurrentPosition()));
		}
	}

}
