// This entire file is part of my masterpiece.
// Ankit Kayastha

package authoring_environment.room.configure_popup;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public interface IConfigure {
	public List<TextField> getFieldList();
	
	public RadioButton getVisibility();
	
	public Button getSaveButton();
	
	public void close();
}
