package authoring_environment;

public interface IAuthorRoom {
	
	public void addObject(IAuthorObject o);
	
	public void removeObject(IAuthorObject o);
	
	public void setSize(double width, double height);
	
	public double[] getSize();
	
	public void setBackground(Object c);
	
	public Object getBackground();
	
	public void setView(View view);
	
	public View getView();
}
