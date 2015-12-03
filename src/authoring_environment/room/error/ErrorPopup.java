package authoring_environment.room.error;

import java.util.ResourceBundle;

import authoring_environment.room.PopupTemplate;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ErrorPopup extends PopupTemplate {
	private static final int LINE_SPACING = 5;
	private static final String ERROR = "Error";
	private static final String OKAY = "Okay";
	
	private Text myText;
	
	public ErrorPopup(ResourceBundle resources, String errorMessage) {
		super(resources, resources.getString(ERROR));
		super.getSaveButton().setText(resources.getString(OKAY));
		super.getSaveButton().setOnAction(e -> this.close());
		super.setAlwaysOnTop(true);
		myText.setText(errorMessage);
	}

	@Override
	protected void setContents() {
		myText = new Text();
		myText.setWrappingWidth(Double.parseDouble(super.getResources().getString(POPUP_WIDTH))-20);
		myText.setTextAlignment(TextAlignment.CENTER);
		myText.setLineSpacing(LINE_SPACING);
		myContentsBox.getChildren().add(myText);
		myContentsBox.setAlignment(Pos.CENTER);
	}

}
