/*
package testing;
import static org.junit.Assert.assertEquals;

import org.junit.*; 
import exceptions.ParameterParseException;
import structures.data.actions.IAction;
import structures.data.actions.MoveTo;
import structures.data.actions.params.IParameter;
import structures.data.actions.params.IParameter.type;
import utils.Point;
import utils.Rectangle;

public class EngineTest {
	
	@Test
	public void main() {
		
		
		IAction action = new MoveTo();
		for (IParameter param : action.getParameters()) {
			if (param.getType() == type.DOUBLE) {
				
				try {
					param.parse("3.14159");
				} catch (ParameterParseException e) {
					
					// This will actually be a useful message:
					System.out.println(e.getMessage());
				}
				
			}
		}
		
		
	}
	
	@Test
	public void rectangleTest() {
		Rectangle rect = new Rectangle(0, 0, 100, 100);
		rect.center();

		assertEquals(false, rect.contains(new Point(0, 51)));
		assertEquals(false, rect.contains(new Point(0, -51)));
		
		rect.setAngle(-45);
		
		assertEquals(true, rect.contains(new Point(0, 51)));
		assertEquals(true, rect.contains(new Point(0, -51)));
		
		Point p = rect.topLeft();
		
		double corner = 50 * Math.sqrt(2);
		assertEquals(-1 * corner, p.y, .01);
		assertEquals(0, p.x, .01);
		
		p = rect.topRight();
		assertEquals(corner, p.x, .01);
		assertEquals(0, p.y, .01);
		
		p = rect.bottomLeft();
		assertEquals(-1 * corner, p.y, .01);
		assertEquals(0, p.x, .01);
		
		p= rect.bottomRight();
		assertEquals(corner, p.x, .01);
		assertEquals(0, p.y, .01);
		
	}
}
*/