package testing;

import engine.physics.ScrollerPhysicsEngine;

public class CustomTests {
	public static void main(String[] args) {
		interpolationTest();
	}
	
	public static void interpolationTest() {
		ScrollerPhysicsEngine engine = new ScrollerPhysicsEngine(1);
		System.out.println(engine.interpolate(0, 0, 2, 1, .1));
	}
}
