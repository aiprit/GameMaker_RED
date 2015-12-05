package groovy;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import exceptions.ReflectionException;
import groovy.lang.GroovyClassLoader;
import utils.Reflection;

public class GroovyObjectInstantiator {
	
	private static Map<String, Class<?>> myCachedClasses = new HashMap<>();
	private static GroovyClassLoader myLoader = new GroovyClassLoader();
	
	public static Object instantiate(String name, Object... args) throws ReflectionException {
		Class<?> type;
		if ((type = myCachedClasses.get(name)) == null) { 
			InputStream stream = GroovyObjectInstantiator.class.getClassLoader().getResourceAsStream("groovy/" + name + ".groovy");
			Scanner s = new Scanner(stream);
			s.useDelimiter("\\A");
			String code = s.hasNext() ? s.next() : "";
			s.close();
	
			type = myLoader.parseClass(code);
			myCachedClasses.put(name, type);
		}
		
		return Reflection.createInstance(type, args);
	}
}
