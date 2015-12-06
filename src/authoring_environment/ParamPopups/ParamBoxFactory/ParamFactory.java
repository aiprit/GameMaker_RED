package authoring_environment.ParamPopups.ParamBoxFactory;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
			String type= label.get(i).getType().toString();
			box.getChildren().add(new Label(label.get(i).getTitle()));
			if(type.equals("INTEGER")||type.equals("DOUBLE")){
				if(label.get(i).getOriginal()!=null){
				box.getChildren().add(new TextField(label.get(i).getOriginal()));
				}
				else{
					box.getChildren().add(new TextField("0"));
				}
			}
			else if(type.equals("STRING")){
				box.getChildren().add(new TextField(label.get(i).getOriginal()));
			}
			else if(type.equals("CHECKBOX")){
				CheckBox cbox = new CheckBox();
				cbox.setSelected((boolean) label.get(i).getValue());
				box.getChildren().add(cbox);
			}
			else if(type.equals("OBJECT_SELECT")||type.equals("SPRITE_SELECT")||
					type.equals("ROOM_SELECT")||type.equals("SELECT")||
					type.equals("SOUND_SELECT")){
				GeneralMenu menu = new GeneralMenu(label.get(i));
				box.getChildren().add(menu.makeMenu());
			}
			else if(label.get(i).getType().toString().equals("GROOVY")){
				if(i==0){
					box.getChildren().add(new TextArea(label.get(i).getOriginal()));
				}
				else
				box.getChildren().add(new TextField(label.get(i).getOriginal()));
			}
			hBoxList.add(box);
		}
		return hBoxList;
	}
}
