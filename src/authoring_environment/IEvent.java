package authoring_environment;

import javafx.event.ActionEvent;

public interface IEvent {
	void addAction(ActionEvent a);
	
	void removeAction(ActionEvent a);
}
