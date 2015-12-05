package structures.run;

import java.util.ArrayList;
import groovy.GroovyObjectInstantiator;

public class RunObjectProxy {
	
	public RunObjectProxy() {		
		
		ArrayList<Object> list = new ArrayList<>();
		list.add(null);
		list.add(null);
		Object obj = GroovyObjectInstantiator.instantiate("MultiObjectProxy", list);
		System.out.println(obj);
		//shell.evaluate("new GroovyRunObjectProxy();");
		
		
	}
	
}
