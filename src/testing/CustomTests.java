package testing;

import utils.Bresenham;
import utils.Utils;

public class CustomTests {
	public static void main(String[] args) {
		interpolationTest();
	}
	
	public static void interpolationTest() {
		System.out.println(Bresenham.interpolate(0, 0, 5, -3));
	}
}
