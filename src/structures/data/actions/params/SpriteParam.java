package structures.data.actions.params;

import java.util.List;

import exceptions.ParameterParseException;
import structures.data.DataSprite;
import utils.Utils;

public class SpriteParam implements IParameter, ISelectable {
	
	private List<DataSprite> myPossibleSprites;
	private DataSprite mySelected;
	private String myOriginal;
	
	public void setSpriteList(List<DataSprite> possibleSprites) {
		myPossibleSprites = possibleSprites;
	}
	
	@Override
	public List<String> getOptions() {
		return Utils.transform(myPossibleSprites, e -> e.getName());
	}

	@Override
	public void parse(String string) throws ParameterParseException {
		myOriginal = string;
		DataSprite found = Utils.first(myPossibleSprites, e -> (e.getName().equals(string)), null);
		if (found == null) {
			throw new ParameterParseException("No valid sprite selected!");
		}
		mySelected = found;
	}

	@Override
	public String getOriginal() {
		return myOriginal;
	}

	@Override
	public String getTitle() {
		return "Object";
	}

	@Override
	public DataSprite getValue() {
		return mySelected;
	}

	@Override
	public type getType() {
		return type.SPRITE_SELECT;
	}

	@Override
	public String getName() {
		return mySelected.getName();
	}

}
