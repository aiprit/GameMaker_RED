//This entire file is part of my masterpiece.
//Nicholas Groszewski

package authoring_environment.Event;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

public class ClassesInPackage {
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/PackageFinder");

	public List<String> getAllClasses(String pckgname) {
		List<String> classNames = new ArrayList<String>();

		try{
			ArrayList classes=new ArrayList();
			// Get a File object for the package
			File directory=null;
			try {
				directory=new File(URLDecoder.decode(Thread.currentThread().getContextClassLoader().getResource(pckgname.replace('.', '/')).getFile(), "UTF-8"));
			} catch(NullPointerException x) {
				throw new ClassNotFoundException(pckgname+" " + r.getString("invalid"));
			}
			if(directory.exists()) {
				// Get the list of the files contained in the package
				String[] files=directory.list();
				for(int i=0; i<files.length; i++) {
					// we are only interested in .class files
					if(files[i].endsWith(r.getString("class"))) {
						// removes the .class extension
						classes.add(Class.forName(pckgname+'.'+files[i].substring(0, files[i].length()-6)));
					}
				}
			} else {
				throw new ClassNotFoundException(pckgname+ " " + r.getString("invalid"));
			}
			Class[] classesA=new Class[classes.size()];
			classes.toArray(classesA);
			for (Class c:classesA) {
				classNames.add(c.getName().substring(pckgname.length()+1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return classNames;
	}
}