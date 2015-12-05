package authoring_environment.main;

import java.util.ResourceBundle;

import authoring_environment.FileHandlers.FileManager;
import exceptions.ResourceFailedException;
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
	private String myGameName;
	
	public RoomIcon(ResourceBundle resources, String gameName) {
		super();
		myGameName = gameName;
		configureVBox();
		myResourceBundle = resources;
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(myResourceBundle.getString("RoomPlaceholderImage")));
		myIcon = new ImageView(image);
		myButton = new Button(myResourceBundle.getString("plus") + " " + myResourceBundle.getString("AddRoom"));
		super.getChildren().addAll(myIcon, myButton);
	}
	
	public RoomIcon(ResourceBundle resources, Image roomSnapshot, String backgroundColor, String roomName, String gameName) {
		super();
		myGameName = gameName;
		configureVBox();
		myResourceBundle = resources;
		myButton = new Button(roomName);
		double iconSize = Double.parseDouble(myResourceBundle.getString("IconSize"));
		Rectangle icon = new Rectangle(iconSize, iconSize);
		setFill(icon, roomSnapshot, backgroundColor);
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
	
	private void setFill(Rectangle icon, Image roomSnapshot, String backgroundColor) {
		try {
			icon.setFill(new ImagePattern(roomSnapshot));
		} catch (NullPointerException e) {
			try {
				Color fill = Color.valueOf(backgroundColor);
				icon.setFill(fill);
			} catch (IllegalArgumentException e1) {
				FileManager fm = new FileManager(myGameName);
				try {
					try {
						icon.setFill(new ImagePattern(fm.getBackground(backgroundColor)));
					} catch (ResourceFailedException e2) {
						icon.setFill(Color.WHITE);
					}
				} catch (NullPointerException e3) {
					icon.setFill(Color.WHITE);
				}
			}
		}
	}
}
