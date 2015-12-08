package authoring_environment.Event;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class ClassesInPackage {
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/PackageFinder");

	public ArrayList<String> getAllClasses(String pckgname) {
		ArrayList<String> classNames = new ArrayList<String>();

		try{
			ArrayList classes=new ArrayList();
			// Get a File object for the package
			File directory=null;
			try {
				directory=new File(URLDecoder.decode(Thread.currentThread().getContextClassLoader().getResource(pckgname.replace('.', '/')).getFile(), "UTF-8"));
			} catch(NullPointerException x) {
				throw new ClassNotFoundException(pckgname+" does not appear to be a valid package");
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
				String name = c.getName().substring(pckgname.length()+1);
				String nameToAdd = "";
				for(int i=0;i<name.length();i++){
					if(Character.isUpperCase(name.charAt(i))&& i!=0){
						nameToAdd += " ";
					}
					nameToAdd += name.charAt(i);
				}
				classNames.add(nameToAdd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return classNames;
	}
}
