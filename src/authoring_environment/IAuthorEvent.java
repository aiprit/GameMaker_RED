package authoring_environment;

import javafx.event.ActionEvent;

public interface IAuthorEvent {
	public void addAction(ActionEvent a);
	
	public void removeAction(ActionEvent a);
}
