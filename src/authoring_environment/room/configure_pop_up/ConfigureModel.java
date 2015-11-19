package authoring_environment.room.configure_pop_up;

import structures.data.DataInstance;
import utils.Vector;

public class ConfigureModel {
	private DataInstance myDataInstance;
	public ConfigureModel(DataInstance dataInstance) {
		myDataInstance = dataInstance;
	}
	public void setVelocity(double xComponent, double yComponent) {
		myDataInstance.setVelocity(new Vector(xComponent, yComponent));
	}
	public void setVisibility(boolean bool) {
		myDataInstance.setVisible(bool);
	}
	public void setAngularVelocity(double angularVelocity) {
		myDataInstance.setAngularVelocity(angularVelocity);
	}
	public void setScale(double scaleX, double scaleY) {
		myDataInstance.setScaleX(scaleX);
		myDataInstance.setScaleY(scaleY);
	}
	public void setAngle(double angle) {
		myDataInstance.setAngle(angle);
	}
}
