class MultiObjectProxy {

	private List<Object> myChildren;

	public MultiObjectProxy(List<Object> children) {
		myChildren = new ArrayList<Object>(children);
	}

	def methodMissing(String name, args) {
		Object returnVal = null;
	    for (Object obj : myChildren) {
	    	returnVal = obj.invokeMethod(name, args);
	    }
	    return returnVal;
	}

	def propertyMissing(String name, Object value) {
		for (Object obj : myChildren) {
			obj.setProperty(name, value);
		}
	}
	
	def propertyMissing(String name) {
		if (myChildren.size() > 0) {
			return myChildren.get(myChildren.size() - 1).getProperty(name);
		}
		return null;
	}
}