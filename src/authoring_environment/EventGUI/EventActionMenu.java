//package authoring_environment.EventGUI;
//
//import java.util.ResourceBundle;
//
//import authoring_environment.main.ActionPopup;
//import authoring_environment.main.EventPopup;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.control.Menu;
//import javafx.scene.control.MenuItem;
//import structures.data.DataGame;
//
//public class EventActionMenu {
//	ResourceBundle r = ResourceBundle.getBundle("authoring_environment/EventGUI/EventGUIResources");
//
//	public Menu makeMenu(DataGame game) {
//		Menu event = new Menu(r.getString("actionTitle"));
//		MenuItem addEvent = new MenuItem(r.getString("addActionTitle"));
//		event.getItems().add(addEvent);
//		ActionPopup popup = new ActionPopup();
//
//		addEvent.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent t) {
//				popup.popup();
//			}
//		});
//		return event;
//	}
//}
