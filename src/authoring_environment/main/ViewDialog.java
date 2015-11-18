/**
 * 
 */
package authoring_environment.main;

import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 * @author loganrooper
 *
 */
public class ViewDialog {
	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");

	public ViewDialog() {
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle(r.getString("viewInputTitle"));

		// Set the button types.
		ButtonType loginButtonType = new ButtonType(r.getString("okay"), ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(20, 150, 10, 10));

		TextField from = new TextField();
		from.setPromptText(r.getString("height"));
		TextField to = new TextField();
		to.setPromptText(r.getString("width"));
		gridPane.add(new Label(r.getString("heightLabel")), 0, 0);
		gridPane.add(from, 1, 0);
		gridPane.add(new Label(r.getString("widthLabel")), 0, 1);
		gridPane.add(to, 1, 1);

		dialog.getDialogPane().setContent(gridPane);

		// Request focus on the username field by default.
		Platform.runLater(() -> from.requestFocus());

		// Convert the result to a username-password-pair when the login
		// button is clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(from.getText(), to.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();
		
//		result.ifPresent(pair -> {
//			boolean check = true;
//			try {
//				double h = Double.parseDouble(height);
//				double w = Double.parseDouble(width);
//			} catch (NumberFormatException nfe) {
//				System.out.println(r.getString("NaN"));
//				check = false;
//			}
//			if (check) {
//				view.setViewHeight(Double.parseDouble(height));
//				view.setViewWidth(Double.parseDouble(width));
//			}
//			setViewSize(pair.getKey(), pair.getValue(), view);
//		});
	}
}
