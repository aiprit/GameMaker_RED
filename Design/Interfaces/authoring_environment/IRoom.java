package authoring_environment;

public interface IRoom throws InvalidRoomException {
	
	public void addObject(IObject o);
	
	public void removeObject(IObject o);
	
	public void setSize(double width, double height);
	
	public double[] getSize();
	
	public void setBackground(Object c);
	
	public Object getBackground();
	
	public void setView(View view);
	
	public View getView();
}
