package structures.data.actions.params;

import java.util.List;

import exceptions.ParameterParseException;
import structures.data.DataSound;
import structures.data.DataSprite;
import structures.data.actions.params.IParameter.type;
import utils.Utils;


public class SoundParam implements IParameter, ISelectable {
	private List<DataSound> myPossibleSounds;
	private DataSound mySelected;
	private String myOriginal;
	private String myTitle;
	public SoundParam(String title) {
		myTitle = title;
	}
	
	public void setSoundList(List<DataSound> possibleSounds) {
		myPossibleSounds = possibleSounds;
	}
	@Override
	public List<String> getOptions() {
		// TODO Auto-generated method stub
		return Utils.transform(myPossibleSounds, e -> e.getName());
	}

	@Override
	public void parse(String string) throws ParameterParseException {
		// TODO Auto-generated method stub
		myOriginal = string;
		DataSound found = Utils.first(myPossibleSounds, e -> (e.getName().equals(string)), null);
		if (found == null) {
			throw new ParameterParseException("No valid sound selected!");
		}
		mySelected = found;
	}

	@Override
	public String getOriginal() {
		// TODO Auto-generated method stub
		return mySelected.getName();
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return myTitle;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return mySelected;
	}

	@Override
	public type getType() {
		// TODO Auto-generated method stub
		return type.SOUND_SELECT;
	}

}
