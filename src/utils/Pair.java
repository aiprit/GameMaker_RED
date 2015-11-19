package utils;

/**
 * A Pair of objects, whose equivalence is determined
 * by the equivalence of the contained objects, even
 * if they're reversed. Useful for Lists and Maps and
 * what not. 
 * 
 * @author Austin
 *
 * @param <T>
 */
public class Pair<T> {
	final public T one, two;
	public Pair (T one, T two) {
		this.one = one;
		this.two = two;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pair<?>) {
			Object one = ((Pair<?>)obj).one;
			Object two = ((Pair<?>)obj).two;
			return (this.one.equals(one) && this.two.equals(two)) || (this.one.equals(two) && this.two.equals(one));
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (one.hashCode() * 17) ^ two.hashCode();
	}
	
	public boolean contains(T object) {
		return one.equals(object) || two.equals(object);
	}
}
