package structures.data;


public interface IDataRoom {
	public void setSize(double width, double height);
	
	public double[] getSize();
	
	public void setBackgroundColor(String color);
	
	public String getBackgroundColor();
}
