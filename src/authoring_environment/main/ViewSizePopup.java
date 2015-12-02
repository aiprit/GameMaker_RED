package authoring_environment.main;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import authoring_environment.room.error.ErrorPopup;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewSizePopup extends Stage {
	private static final double TEXT_SCALE = 1.7;
	private static final String DEFAULT_RESOURCE_PACKAGE = "css/";
	private static final String STYLESHEET = "authoring.css";
	private static final int HEIGHT_PADDING = 150;
	private static final int WIDTH_PADDING = 50;
	private static final int SMALL_SPACE = 10;
	private static final int SPACING = 20;
	
	private ResourceBundle myResources;
	private VBox myContents;
	private HBox myTextFields;
	private HBox myButtons;
	private Button mySaveButton;
	private Button myPreviewButton;
	private TextField myViewWidth;
	private TextField myViewHeight;
	private Rectangle myViewPreview;
	
	public ViewSizePopup(ResourceBundle resources) {
		super();
		myResources = resources;
		super.setTitle(myResources.getString("ViewSize"));
		initializeContents();
	}
	
	private void initializeContents() {
		myContents = new VBox();
		myContents.setSpacing(SPACING);
		myContents.setAlignment(Pos.CENTER);
		myContents.setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));
		initializePreview();
		initializeTextFields();
		initializeButtons();
		Scene s = new Scene(myContents);
		s.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
		super.setScene(s);
	}
	
	private void initializePreview() {
		StackPane pane = new StackPane();
		myViewPreview = new Rectangle();
		List<Integer> viewRGB = Arrays.asList(myResources.getString("ViewColor").split(",")).stream()
				.map(val -> Integer.parseInt(val))
				.collect(Collectors.toList());
		myViewPreview.setStroke(Color.rgb(viewRGB.get(0), viewRGB.get(1), viewRGB.get(2)));
		myViewPreview.setFill(Color.rgb(viewRGB.get(0), viewRGB.get(1), viewRGB.get(2), 
				Double.parseDouble(myResources.getString("ViewOpacity"))));
		Label actualSize = new Label(myResources.getString("ActualSize"));
		actualSize.setScaleX(TEXT_SCALE);
		actualSize.setScaleY(TEXT_SCALE);
		actualSize.setLabelFor(myViewPreview);
		actualSize.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(myViewPreview, actualSize);
		myContents.getChildren().add(pane);
	}
	
	private void initializeTextFields() {
		Text viewWidthTitle = new Text(myResources.getString("ViewWidthTitle"));
		Text viewHeightTitle = new Text(myResources.getString("ViewHeightTitle"));
		myViewWidth = new TextField();
		myViewHeight = new TextField();
		HBox viewWidth = new HBox(viewWidthTitle, myViewWidth);
		viewWidth.setSpacing(SMALL_SPACE);
		HBox viewHeight = new HBox(viewHeightTitle, myViewHeight);
		viewHeight.setSpacing(SMALL_SPACE);
		myTextFields = new HBox();
		myTextFields.setSpacing(SPACING);
		myTextFields.getChildren().addAll(viewWidth, viewHeight);
		myTextFields.setAlignment(Pos.CENTER);
		myContents.getChildren().add(myTextFields);
	}
	
	private void initializeButtons() {
		mySaveButton = new Button(myResources.getString("Save"));
		myPreviewButton = new Button(myResources.getString("Preview"));
		myPreviewButton.setOnAction(e -> updatePreview());
		myButtons = new HBox();
		myButtons.setSpacing(SPACING);
		myButtons.setAlignment(Pos.CENTER_RIGHT);
		myButtons.getChildren().addAll(myPreviewButton, mySaveButton);
		myContents.getChildren().add(myButtons);
	}
	
	public void updatePreview() {
		int minSize = (int)Double.parseDouble(myResources.getString("ViewMinimumSize"));
		int width = (int)Double.parseDouble(myViewWidth.getText());
		int height = (int)Double.parseDouble(myViewHeight.getText());
		if (width < minSize || height < minSize) {
			launchMinSizeErrorPopup(minSize);
			return;
		}
		myViewPreview.setWidth(width);
		myViewPreview.setHeight(height);
		super.setWidth(width + WIDTH_PADDING);
		super.setHeight(height + HEIGHT_PADDING);
	}
	
	public void launchMinSizeErrorPopup(int minSize) {
		String errorMessage = String.format(myResources.getString("MinimumViewSizeException"), minSize);
		ErrorPopup error = new ErrorPopup(myResources, errorMessage);
	}
	
	public Button getSaveButton() {
		return mySaveButton;
	}

	public TextField getViewWidth() {
		return myViewWidth;
	}
	
	public void setViewWidth(TextField myViewWidth) {
		this.myViewWidth = myViewWidth;
	}
	
	public TextField getViewHeight() {
		return myViewHeight;
	}
	
	public void setViewHeight(TextField myViewHeight) {
		this.myViewHeight = myViewHeight;
	}

}
