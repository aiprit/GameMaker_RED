package authoring_environment.OtherGUI;

import java.util.List;
import java.util.ResourceBundle;

import authoring_environment.EventGUI.EventRightPane;
import exceptions.ParameterParseException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import structures.data.actions.CustomCode;
import structures.data.actions.IAction;
import structures.data.actions.params.IParameter;
import structures.data.events.IDataEvent;

public class ActionGUI {

	private Stage myStage;
	private Group myRoot;
	private EventRightPane myRight;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Action/GUI/ActionGUIResources");
	public ActionGUI(Stage stage,EventRightPane right){
		try{
			myRight = right;
			myRoot = new Group();
			myStage = stage;
			init();
		}
		catch (NullPointerException e){
		}

	}
	public void init() {
		myStage.setTitle(r.getString("title"));
		TextArea text = new TextArea(r.getString("textfield"));
		text.setMinWidth(Integer.parseInt(r.getString("screenWidth")));
		text.setMinHeight(Integer.parseInt(r.getString("screenHeight")));
		text.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER) && ke.isShiftDown()) {

                  try {
					createCustom(text.getText());
				} catch (ParameterParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                  close(ke);
                }
            }
        });
		myRoot.getChildren().add(text);
		myStage.setScene(new Scene(myRoot ));
		myStage.show();

	}
	protected void createCustom(String text) throws ParameterParseException {
		IAction action = new CustomCode();
		List<IParameter> param = action.getParameters();
		for(IParameter p :param){
			p.parse(text);
		}
		myRight.add(action);
	}
	protected void close(KeyEvent e) {
		Node  source = (Node)  e.getSource();
		 Stage stage  = (Stage) source.getScene().getWindow();
		 stage.close();
	}
}
