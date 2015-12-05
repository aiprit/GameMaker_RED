package authoring_environment.ParamPopups.ParamBoxFactory;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import structures.data.actions.params.IParameter;

public class ParamFactory {

	public List<HBox> HBoxFactory(List<IParameter> label) {
		List<HBox> hBoxList = new ArrayList<HBox>();
		HBox boxtop = new HBox(10);
		boxtop.getChildren().add(new Label("Please Set Variables"));
		hBoxList.add(boxtop);
		for (int i = 0; i < label.size(); i++) {
			HBox box = new HBox(10);
			box.getChildren().add(new Label(label.get(i).getTitle()));
			if(label.get(i).getType().toString().equals("INTEGER")){
				box.getChildren().add(new TextField(label.get(i).getOriginal()));
			}
			else if(label.get(i).getType().toString().equals("STRING")){
				box.getChildren().add(new TextField(label.get(i).getOriginal()));
			}
			else if(label.get(i).getType().toString().equals("DOUBLE")){
				box.getChildren().add(new TextField(label.get(i).getOriginal()));
			}
			else if(label.get(i).getType().toString().equals("CHECKBOX")){
				CheckBox cbox = new CheckBox();
				cbox.setSelected((boolean) label.get(i).getValue());
				box.getChildren().add(cbox);
			}
			else if(label.get(i).getType().toString().equals("OBJECT_SELECT")){
				ObjectMenu menu = new ObjectMenu(label.get(i));
				box.getChildren().add(menu.makeMenu());
			}
			else if(label.get(i).getType().toString().equals("SPRITE_SELECT")){
				SpriteMenu menu = new SpriteMenu(label.get(i));
				box.getChildren().add(menu.makeMenu());
			}
			else if(label.get(i).getType().toString().equals("ROOM_SELECT")){
				RoomMenu menu = new RoomMenu(label.get(i));
				box.getChildren().add(menu.makeMenu());
			}
			else if(label.get(i).getType().toString().equals("SELECT")){
				SelectMenu menu = new SelectMenu(label.get(i));
				box.getChildren().add(menu.makeMenu());
			}
			else if(label.get(i).getType().toString().equals("GROOVY")){
				box.getChildren().add(new TextField(label.get(i).getOriginal()));
			}
			hBoxList.add(box);
		}
		return hBoxList;
	}
}
