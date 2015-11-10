package structures.data;

import java.util.List;

import authoring_environment.IAuthorObject;
import authoring_environment.IAuthorRoom;
import authoring_environment.View;

public class DataRoom implements IAuthorRoom {
    List<DataObject> roomObjects;
    DataView view;
    String backgroundImage;
    double width, height;
	@Override
	public void addObject(IAuthorObject o) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeObject(IAuthorObject o) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setSize(double width, double height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public double[] getSize() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setBackground(Object c) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object getBackground() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setView(View view) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return null;
	}
}
