package authoring_environment.main;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class RoomIcon extends VBox {
	private static final int VBOX_HEIGHT = 150;
	private static final int INSET_VALUE = 20;
	private static final int BOX_SPACING = 10;
	
	private ResourceBundle myResourceBundle;
	private Node myIcon;
	private Button myButton;
	private Button myDeleteButton;
	
	public RoomIcon(ResourceBundle resources) {
		super();
		configureVBox();
		myResourceBundle = resources;
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(myResourceBundle.getString("RoomPlaceholderImage")));
		myIcon = new ImageView(image);
		myButton = new Button(myResourceBundle.getString("plus") + " " + myResourceBundle.getString("AddRoom"));
		super.getChildren().addAll(myIcon, myButton);
	}
	
	public RoomIcon(ResourceBundle resources, String backgroundColor, String roomName) {
		super();
		configureVBox();
		myResourceBundle = resources;
		myButton = new Button(roomName);
		double iconSize = Double.parseDouble(myResourceBundle.getString("IconSize"));
		Rectangle icon = new Rectangle(iconSize, iconSize);
		setFill(icon, backgroundColor);
		myIcon = icon;
		myDeleteButton = new Button(myResourceBundle.getString("Delete"));
		super.getChildren().addAll(myIcon, myButton, myDeleteButton);
	}
	
	public Button getDeleteButton() {
		return myDeleteButton;
	}

	private void configureVBox() {
		super.setSpacing(BOX_SPACING);
		super.setAlignment(Pos.CENTER);
		super.setPadding(new Insets(INSET_VALUE, INSET_VALUE, INSET_VALUE, INSET_VALUE));
		super.setPrefHeight(VBOX_HEIGHT);
	}
	
	public Button getButton() {
		return myButton;
	}
	
	private void setFill(Rectangle icon, String backgroundColor) {
		try {
			Color fill = Color.valueOf(backgroundColor);
			icon.setFill(fill);
		} catch (IllegalArgumentException e) {
			try {
				icon.setFill(new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream(backgroundColor))));
			} catch (NullPointerException e2) {
				icon.setFill(Color.WHITE);
			}
		}
	}
}
