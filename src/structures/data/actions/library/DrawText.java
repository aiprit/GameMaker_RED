package structures.data.actions.library;

import structures.data.DataAction;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.StringParam;

public class DrawText extends DataAction{

	public DrawText(){
		init(new StringParam("DrawText"), new DoubleParam("XText"),
				new DoubleParam("YText"), new DoubleParam("FontSize"));
	}

	@Override
	public String getTitle() {
		return "DrawText";
	}

	@Override
	public String getDescription() {
		return String.format("draws %s on the screen", get("DrawText").getValue(), get("XText").getValue(),
				get("YText").getValue(), get("FontSize").getValue());
	}

	@Override
	protected String getSyntax() {
		return "library.draw_text('%s');";
	}

}
