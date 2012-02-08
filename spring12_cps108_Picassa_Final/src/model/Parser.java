package model;

import java.util.*;
import model.expression.*;

/**
 * Parses a string into an expression tree based on rules for arithmetic.
 * 
 * Due to the nature of the language being parsed, a recursive descent parser is
 * used http://en.wikipedia.org/wiki/Recursive_descent_parser
 * 
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions, some functions)
 */
public class Parser {

	// state of the parser
	private int myCurrentPosition;
	private String myInput;
	private List<ExpressionFactory> expressionFactoryTypeList = new ArrayList<ExpressionFactory>();

	public Parser() {
		// Zero Argument Functions
		expressionFactoryTypeList.add(Randoms.getFactory());

		// Unary Argument Functions
		expressionFactoryTypeList.add(Negate.getFactory());
		expressionFactoryTypeList.add(Floor.getFactory());
		expressionFactoryTypeList.add(Ceil.getFactory());
		expressionFactoryTypeList.add(Abs.getFactory());
		expressionFactoryTypeList.add(Clamp.getFactory());
		expressionFactoryTypeList.add(Wrap.getFactory());
		expressionFactoryTypeList.add(Sin.getFactory());
		expressionFactoryTypeList.add(Cos.getFactory());
		expressionFactoryTypeList.add(Tan.getFactory());
		expressionFactoryTypeList.add(Atan.getFactory());
		expressionFactoryTypeList.add(Log.getFactory());
		expressionFactoryTypeList.add(RgbToYCrCb.getFactory());
		expressionFactoryTypeList.add(YCrCbtoRGB.getFactory());

		// Binary Argument Functions
		expressionFactoryTypeList.add(Plus.getFactory());
		expressionFactoryTypeList.add(Minus.getFactory());
		expressionFactoryTypeList.add(Exponentiate.getFactory());
		expressionFactoryTypeList.add(Mod.getFactory());
		expressionFactoryTypeList.add(Divide.getFactory());
		expressionFactoryTypeList.add(Multiply.getFactory());

		// Ternary Argument Functions
		expressionFactoryTypeList.add(Color.getFactory());
		expressionFactoryTypeList.add(Let.getFactory());
		expressionFactoryTypeList.add(If.getFactory());

		// Infinite Argument Functions
		expressionFactoryTypeList.add(Sum.getFactory());
		expressionFactoryTypeList.add(Product.getFactory());
		expressionFactoryTypeList.add(Average.getFactory());
		expressionFactoryTypeList.add(Min.getFactory());
		expressionFactoryTypeList.add(Max.getFactory());

		// Other Functions
		expressionFactoryTypeList.add(PerlinColor.getFactory());
		expressionFactoryTypeList.add(PerlinBW.getFactory());
		expressionFactoryTypeList.add(NumberExpression.getFactory());
		expressionFactoryTypeList.add(VariableExpression.getFactory());
		expressionFactoryTypeList.add(AssignmentExpression.getFactory());
	}

	/**
	 * Converts given string into expression tree.
	 * 
	 * @param input
	 *            expression given in the language to be parsed
	 * @return expression tree representing the given formula
	 */
	public Expression makeExpression(String input) {
		myInput = input;
		myCurrentPosition = 0;
		Expression result = parseExpression(myInput, this);
		skipWhiteSpace();
		if (notAtEndOfString()) {
			throw new ParserException(
					"Unexpected characters at end of the string: "
							+ myInput.substring(myCurrentPosition),
					ParserException.Type.EXTRA_CHARACTERS);
		}
		return result;
	}

	public Expression parseExpression(String input, Parser parser) {
		for (ExpressionFactory expressionFactoryType : expressionFactoryTypeList) {
			if (expressionFactoryType.isThisKindOfExpression(myInput, this))
				return expressionFactoryType.parseExpression(myInput, this);
		}
		return null;
	}

	public int getParserCurrentPosition() {
		return myCurrentPosition;
	}

	public void setParserCurrentPosition(int newCurrentPosition) {
		myCurrentPosition = newCurrentPosition;
	}

	public void skipWhiteSpace() {
		while (notAtEndOfString()
				&& Character.isWhitespace(getParserCurrentCharacter())) {
			myCurrentPosition++;
		}
	}

	 public void advanceCurrentPosition(int chars)
	    {
	        myCurrentPosition += chars;
	    }
	
	public char getParserCurrentCharacter() {
		return myInput.charAt(myCurrentPosition);
	}

	private boolean notAtEndOfString() {
		return myCurrentPosition < myInput.length();
	}
}
