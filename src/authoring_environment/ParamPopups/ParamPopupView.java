package authoring_environment.ParamPopups;

import java.util.List;
import java.util.ResourceBundle;

import authoring_environment.ParamPopups.ParamBoxFactory.ParamFactory;
import authoring_environment.room.configure_popup.GridPaneHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import structures.data.actions.params.IParameter;

public class ParamPopupView extends Stage{
	private Button save,cancel;
	private VBox popUp;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ParamPopups/ParamResources");;
	private RadioButton visibilityButton;
	private List<HBox> fieldList;
	private Scene myScene;
	private Stage myStage;
	public ParamPopupView(List<IParameter> list){
		popUp = new VBox();
		initializePopUp(list);
	}
	private void initializePopUp(List<IParameter> list) {
		ParamFactory handler = new ParamFactory();
		//String[] labelStrings = {myResources.getString(VELOCITY_FIELD_X), myResources.getString(VELOCITY_FIELD_Y), myResources.getString(ANGULAR_VELOCITY), myResources.getString(SCALE_X), myResources.getString(SCALE_Y), myResources.getString(ANGLE), myResources.getString(TRANSPARENCY)};
		fieldList = handler.HBoxFactory(list);
		for (HBox box : fieldList) {
			popUp.getChildren().add(box);
		}
		HBox visibility = new HBox();
		popUp.getChildren().add(visibility);
		save = new Button(r.getString("Save"));
		//save = new Button(r.getString("Save"));
		popUp.getChildren().addAll(save);
		popUp.setAlignment(Pos.CENTER);
		popUp.minWidth(100);
		myScene = new Scene(popUp);
		this.setScene(myScene);
		//this.show();

	}
	public Button getSaveButton() {
		return save;
	}
	public Button getCancelButton() {
		return cancel;
	}

	public List<HBox> getFieldList() {
		return fieldList;
	}

}
