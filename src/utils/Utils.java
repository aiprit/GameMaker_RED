package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {
	
	@FunctionalInterface
	public interface Converter<A, B, C extends Throwable> {
		public B run(A input) throws C;
	}
	
	@FunctionalInterface
	public interface Converter2<A, B, C, D extends Throwable> {
		public C run(A input1, B input2) throws D;
	}
	
	@FunctionalInterface
	public interface Condition<A, E extends Throwable> {
		public boolean condition(A input) throws E;
	}
	
	/**
	 * Same as below, but for a single input List.
	 * 
	 * EXAMPLE:
	 * List<String> filenames = {"hello.txt", "something.txt"}
	 * List<Boolean> exists = transform(filenames, e -> Files.exists(e));
	 * 
	 * @param input
	 * @param converter
	 * @return
	 * @throws C
	 */
	public static <A, B, C extends Throwable> List<B> transform(List<A> input, Converter<A, B, C> converter) throws C {
		List<B> output = new ArrayList<>(input.size());
		for (int i = 0; i < input.size(); i++) {
			output.add(converter.run(input.get(i)));
		}
		return output;
	}
	
	/**
	 * Takes in Lists of type A and B and returns a single List of type C.
	 * The function to convert can be given as a lambda as the third arg.
	 * However, this will also 'upconvert' the Lists if one of them has
	 * only one item and the other has many. In other words, it will
	 * repeatedly use the transforming function given, with the single-
	 * item list item being used repeatedly for every one of the big list.
	 * 
	 * If two different sized lists are given, and one of them isn't only
	 * a single item, we will throw an error.
	 * 
	 * EXAMPLE:
	 * List<Integer> ints1 = {5, 6, 7, 8};
	 * List<Integer> ints2 = {3, 2, 1, 0}
	 * List<String> stringDotProduct = transform(ints1, ints2, (x, y) -> Integer.toString(x * y));
	 * (= ["15", "12", "7", "0"])
	 * 
	 * @param input1
	 * @param input2
	 * @param converter
	 * @return
	 * @throws D
	 */
	public static <A, B, C, D extends Throwable> List<C> transform(List<A> input1, List<B> input2, Converter2<A, B, C, D> converter) throws D {
		if ((input1.size() == input2.size()) && (input2.size() == 1)) {
			return Collections.singletonList(converter.run(input1.get(0), input2.get(0)));
		}
		if (input1.size() > 1 && input2.size() > 1) {
			if (input1.size() != input2.size()) {
				String err = String.format("A function somehow received two different sized inputs: %s and %s", input1, input2);
				throw new InternalError(err);
			} else {
				List<C> output = new ArrayList<>(input1.size());
				for (int i = 0; i < input1.size(); i++) {
					output.add(converter.run(input1.get(i), input2.get(i)));
				}
				return output;
			}
		}		
		if (input1.size() > 1) {
			List<C> output = new ArrayList<>(input1.size());
			for (int i = 0; i < input1.size(); i++) {
				output.add(converter.run(input1.get(i), input2.get(0)));
			}
			return output;
		}
		if (input2.size() > 1) {
			List<C> output = new ArrayList<>(input2.size());
			for (int i = 0; i < input2.size(); i++) {
				output.add(converter.run(input1.get(0), input2.get(i)));
			}
			return output;
		}
		throw new InternalError("Not possible");
	}
	
	public static double constrain(double value, double min, double max) {
		return Math.min(max, Math.max(min, value));
	}
	
	public static List<Point> interpolate(double x1, double y1, double x2, double y2, double resolution) {
		Vector point = (new Vector(x2 - x1, y2 - y1)).setLength(resolution);
		int times = (int)(Math.hypot(x2 - x1, y2 - y1) / resolution);
		List<Point> list = new ArrayList<>(times + 1);	
		double cursorX = x1;
		double cursorY = y1;
		for (int i = 0; i < times; i++) {
			list.add(new Point(cursorX, cursorY));
			cursorX += point.x;
			cursorY += point.y;
		}
		list.add(new Point(x2, y2));
		return list;
	}
	
	public static <T, E extends Throwable> T first(List<T> input, Condition<T, E> condition, T defaultValue) throws E {
		T item;
		for (int i = 0; i < input.size(); i++) {
			item = input.get(i);
			if (condition.condition(item)) {
				return item;
			}
		}
		return defaultValue;
	}
	
	/**
	 * Is the point (cx, cy) to the left, right, or on
	 * the line defined by (ax, ay) -> (bx, by)?
	 * 
	 * Written by Al Globus on Stack Overflow
	 * https://stackoverflow.com/questions/1560492/
	 * how-to-tell-whether-a-point-is-to-the-right-or-left-side-of-a-line
	 * 
	 * @return integer code for which side of the line ab c is on.  1 means
	 * left side, -1 means right side.  Returns
	 * 0 if all three are on a line
	 */
	public static int findSide(Point a, Point b, Point c) {
	    if (Math.abs(b.x-a.x) < .000000001) { // vertical line
	        if (c.x < b.x) {
	            return b.y > a.y ? 1 : -1;
	        }
	        if (c.x > b.x) {
	            return b.y > a.y ? -1 : 1;
	        } 
	        return 0;
	    }
	    if (Math.abs(b.y-a.y) < .000000001) { // horizontal line
	        if (c.y < b.y) {
	            return b.x > a.x ? -1 : 1;
	        }
	        if (c.y > b.y) {
	            return b.x > a.x ? 1 : -1;
	        } 
	        return 0;
	    }
	    double slope = (b.y - a.y) / (b.x - a.x);
	    double yIntercept = a.y - a.x * slope;
	    double cSolution = (slope * c.x) + yIntercept;
	    if (slope != 0) {
	        if (c.y > cSolution) {
	            return b.x > a.x ? 1 : -1;
	        }
	        if (c.y < cSolution) {
	            return b.x > a.x ? -1 : 1;
	        }
	        return 0;
	    }
	    return 0;
	}
	
	private static final double TWO_POW_450 = Double.longBitsToDouble(0x5C10000000000000L);
	private static final double TWO_POW_N450 = Double.longBitsToDouble(0x23D0000000000000L);
	private static final double TWO_POW_750 = Double.longBitsToDouble(0x6ED0000000000000L);
	private static final double TWO_POW_N750 = Double.longBitsToDouble(0x1110000000000000L);
	
	public static double hypot(double x, double y) {
	    x = Math.abs(x);
	    y = Math.abs(y);
	    if (y < x) {
	        double a = x;
	        x = y;
	        y = a;
	    } else if (!(y >= x)) { // Testing if we have some NaN.
	        if ((x == Double.POSITIVE_INFINITY) || (y == Double.POSITIVE_INFINITY)) {
	            return Double.POSITIVE_INFINITY;
	        } else {
	            return Double.NaN;
	        }
	    }
	    if (y-x == y) { // x too small to substract from y
	        return y;
	    } else {
	        double factor;
	        if (x > TWO_POW_450) { // 2^450 < x < y
	            x *= TWO_POW_N750;
	            y *= TWO_POW_N750;
	            factor = TWO_POW_750;
	        } else if (y < TWO_POW_N450) { // x < y < 2^-450
	            x *= TWO_POW_750;
	            y *= TWO_POW_750;
	            factor = TWO_POW_N750;
	        } else {
	            factor = 1.0;
	        }
	        return factor * Math.sqrt(x*x+y*y);
	    }
	}
	
	
}
