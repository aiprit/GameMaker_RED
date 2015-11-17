package authoring_environment.ObjectGUI.leftPane;

import java.util.ResourceBundle;

import authoring_environment.EventPopup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;

public class MakeKeyPressMenu {
	ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/leftPane/KeyPressResources");

	public Menu makeMainMenu() {
		Menu event = new Menu(r.getString("KeyTitle"));
//		MenuItem addEvent = new MenuItem(r.getString("addEventTitle"));	
//		event.getItems().add(addEvent);
//		EventPopup popup = new EventPopup();
//		
//		addEvent.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent t) {
//				popup.popup();
//			}
//		});
		return event;
	}
	public Menu makeAZMenu(){
		Menu menu = new Menu(r.getString("AZTitle"));
		for(char alphabet = 'A'; alphabet <= 'Z';alphabet++) {
			MenuItem addEvent = new MenuItem(String.valueOf(alphabet));	
			menu.getItems().add(addEvent);
			EventPopup popup = new EventPopup();
			addEvent.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					popup.popup();
				}
				KeyCode c = A;
			});
		}
	}
}
