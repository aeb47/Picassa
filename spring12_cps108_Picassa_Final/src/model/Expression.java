package model;

import java.util.*;

/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical functions and the
 * leaves represent constant values.
 * 
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 */
abstract public class Expression {
	abstract public RGBColor evaluate(Map<String, Expression> map);

	abstract public boolean isThisKindOfExpression(String parseableString,
			Parser parser);

	abstract public Expression parseExpression(String parseableString,
			Parser parser);

	abstract public List<Expression> getSubExpressionList();
}
