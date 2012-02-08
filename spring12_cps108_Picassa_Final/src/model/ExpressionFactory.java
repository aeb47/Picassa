package model;

public class ExpressionFactory {
	private Expression myExpression;

	public ExpressionFactory(Expression expression) {
		myExpression = expression;
	}

	public boolean isThisKindOfExpression(String parseableString, Parser parser) {
		return myExpression.isThisKindOfExpression(parseableString, parser);
	}

	public Expression parseExpression(String parseableString, Parser parser) {
		return myExpression.parseExpression(parseableString, parser);
	}
}
