package authoring_environment.ParamPopups.ParamBoxFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import structures.data.actions.params.IParameter;

public class ParamFactory {

	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ParamPopups/ParamBoxFactory");
	public List<HBox> HBoxFactory(List<IParameter> label) {
		List<HBox> hBoxList = new ArrayList<HBox>();
		HBox boxtop = new HBox(10);
		boxtop.getChildren().add(new Label(r.getString("setVars")));
		hBoxList.add(boxtop);
		for (int i = 0; i < label.size(); i++) {
			HBox box = new HBox(10);
			String type= label.get(i).getType().toString();
			box.getChildren().add(new Label(label.get(i).getTitle()));
			if(type.equals(r.getString("int"))||type.equals(r.getString("double"))){
				if(label.get(i).getOriginal()!=null){
				box.getChildren().add(new TextField(label.get(i).getOriginal()));
				}
				else{
					box.getChildren().add(new TextField(r.getString("zero")));
				}
			}
			else if(type.equals(r.getString("string"))){
				box.getChildren().add(new TextField(label.get(i).getOriginal()));
			}
			else if(type.equals(r.getString("checkBox"))){
				CheckBox cbox = new CheckBox();
				cbox.setSelected((boolean) label.get(i).getValue());
				box.getChildren().add(cbox);
			}
			else if(type.equals(r.getString("objectSelect"))||type.equals(r.getString("spriteSelect"))||
					type.equals(r.getString("roomSelect"))||type.equals(r.getString("select"))||
					type.equals(r.getString("soundSelect"))){
				GeneralMenu menu = new GeneralMenu(label.get(i));
				box.getChildren().add(menu.makeMenu());
			}
			else if(label.get(i).getType().toString().equals(r.getString("groovy"))){
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
