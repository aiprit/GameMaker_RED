package authoring_environment.ParamPopups;

import java.util.List;

import structures.data.actions.params.IParameter;
import structures.data.events.IDataEvent;

public class ParamController {

	private ParamPopupView view;
	private ParamModel model;

	public ParamController(IDataEvent e ,List<IParameter> list){
		ParamPopupView view = new ParamPopupView(list);
		ParamModel model = new ParamModel(e);
	}
}
