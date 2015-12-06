package authoring_environment.Event;

import java.util.ArrayList;
import java.util.Collections;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.image.Image;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.interfaces.IAction;
import structures.data.interfaces.IDataEvent;

public class EventModel {
	private DataObject myObject;
	private IDataEvent myEvent;
	private ObservableList<IAction> alist;
	private IObjectInterface game;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/EventGUIResources");
	public EventModel(DataObject obj,IDataEvent e,IObjectInterface inter){
		myObject = obj;
		myEvent = e;
		game = inter;
		ObservableMap<IDataEvent, ObservableList<IAction>> map = myObject.getEvents();
		if(map.containsKey(myEvent)){
			alist = map.get(myEvent);
		}
		else{
			alist = FXCollections.observableList(new ArrayList<IAction>());
		}
	}
	public EventModel(DataObject obj){
		myObject = obj;
	}
	public void saveEvent(){
		myObject.bindEvent(myEvent,alist);
	}
	public ObservableList<IAction> getActions(){
		return alist;
	}
	public void addAction(IAction a,int index) {
		if(index<0){
			alist.add(a);
		}
		else{
			alist.add(index,a);
		}
	}
	public void deleteAction(IAction a) {
		alist.remove(a);
	}
	public ObservableList<String> initTempActions(){
		ClassesInPackage classes = new ClassesInPackage();
		ObservableList<String> list = FXCollections.observableList(new ArrayList<String>());
		//Enumeration <String> keys = l.getKeys();

		for(int i=1;i<6;i++){
			String str =r.getString("action"+i);
			String[] ar = str.split("\\.");
			str =  capitalize(ar[ar.length-1])+" "+r.getString("type");
			list.add(str);
			for (String s:classes.getAllClasses(r.getString("actions"+i))) {
				list.add(s);
			}

		}
		//List<String> keylist = Collections.list(keys);
		//Collections.sort(list);
		//		for (String str:keylist) {
		//			String value = l.getString(str);
		//			list.add(value);
		//
		//		};
		return list;
	}
	public IDataEvent getEvent(){
		return myEvent;
	}
	public Image getImage() {
		return new Image(getClass().getClassLoader().getResourceAsStream(r.getString("image")),
				Integer.parseInt(r.getString("imagesize")),Integer.parseInt(r.getString("imagesize")),false,false);
	}
	public IObjectInterface getGame(){
		return game;
	}
	public ResourceBundle getBundle(){
		return r;
	}
	private String capitalize(final String line) {
		   return Character.toUpperCase(line.charAt(0)) + line.substring(1);
		}
}
