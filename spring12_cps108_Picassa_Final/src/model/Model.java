package model;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 */
public class Model {
	private static final double DOMAIN_MIN = -1;
	private static final double DOMAIN_MAX = 1;
	public static final int NUM_FRAMES = 100;
	private double myCurrentTime = -1;
	private Map<String, Expression> VariablesStorage = new HashMap<String, Expression>();

	/**
	 * Advance to the next frame in the animation.
	 */
	public void reset() {
		myCurrentTime = -1;
	}

	/**
	 * Advance to the next frame in the animation.
	 */
	public void nextFrame() {
		myCurrentTime += 0.5 / NUM_FRAMES;
	}

	/**
	 * Evaluate an expression for each point in the image.
	 */
	public Pixmap evaluate(String input, Dimension size) {
		Pixmap result = new Pixmap(size);
		// create expression to evaluate just once
		Expression toEval = new Parser().makeExpression(input);
		// evaluate at each pixel
		for (int imageY = 0; imageY < size.height; imageY++) {
			double evalY = imageToDomainScale(imageY, size.height);
			for (int imageX = 0; imageX < size.width; imageX++) {
				double evalX = imageToDomainScale(imageX, size.width);
				Expression Xexp = new NumberExpression(new RGBColor(evalX));
				Expression Yexp = new NumberExpression(new RGBColor(evalY));
				Expression Texp = new NumberExpression(new RGBColor(
						myCurrentTime));
				VariablesStorage.put("x", Xexp);
				VariablesStorage.put("y", Yexp);
				VariablesStorage.put("t", Texp);
				result.setColor(imageX, imageY,
						toEval.evaluate(VariablesStorage).toJavaColor());
			}
		}
		nextFrame();
		return result;
	}

	/**
	 * Convert from image space to domain space.
	 */
	protected double imageToDomainScale(int value, int bounds) {
		double range = DOMAIN_MAX - DOMAIN_MIN;
		return ((double) value / bounds) * range + DOMAIN_MIN;
	}
}
