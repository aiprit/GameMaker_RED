package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	
}
