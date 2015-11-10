package authoring_environment;

import javafx.event.ActionEvent;

public interface IEvent throws InvalidEventException {
	public void addAction(ActionEvent a);
	
	public void removeAction(ActionEvent a);
}
