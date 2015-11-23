package authoring_environment.Action;


import java.util.List;

import exceptions.ParameterParseException;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import structures.data.actions.IAction;
import structures.data.actions.params.IParameter;

public class ActionController {
	ActionView myView;
	ActionModel myModel;
	public ActionController(List<IAction> list){
		myView = new ActionView();
		myModel = new ActionModel(list);
		initAll();
	}

	public void initAll(){
		myView.getTextArea().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER) && ke.isShiftDown()) {

					try {
						createCustom(myView.getTextArea().getText());
					} catch (ParameterParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					close(ke);
				}
			}
		});
	}
	private void close(KeyEvent e) {
		Node  source = (Node)  e.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
	}

	private void createCustom(String text) throws ParameterParseException{
		IAction action = new CustomCode();
		List<IParameter> param = action.getParameters();
		for(IParameter p :param){
			p.parse(text);
		}
		myModel.add(action);
	}
}
