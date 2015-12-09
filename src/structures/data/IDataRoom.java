package structures.data;


public interface IDataRoom {
	void setSize(double width, double height);
	
	double[] getSize();
	
	void setBackgroundColor(String color);
	
	String getBackgroundColor();
}
