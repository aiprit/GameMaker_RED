package testing;

import utils.Utils;

public class CustomTests {
	public static void main(String[] args) {
		interpolationTest();
	}
	
	public static void interpolationTest() {
		System.out.println(Utils.interpolate(0, 0, 2, 1, .1));
	}
}
