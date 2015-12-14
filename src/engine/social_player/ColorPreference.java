// This entire file is part of my masterpiece.
// Bailey Wall

package engine.social_player;

public class ColorPreference extends PlayerPreference {
	
	private String colorPreference;
	
	public ColorPreference(String color){
		colorPreference = color;
	}

	@Override
	public String getTextKey() {
		return "color";
	}
	
	@Override
	public String getPreference(){
		return colorPreference;
	}
	
	@Override
	public void setPreference(String value){
		colorPreference = value;
	}

}
