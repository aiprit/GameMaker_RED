package authoring_environment.room.configure_pop_up;

public class ConfigureController {
	
	public ConfigureController() {
		ConfigureView configure = new ConfigureView();
		ConfigureModel model = new ConfigureModel();
		configure.getSaveButton().setOnAction(e -> onSave());
	}
	
	private void onSave() {
		
	}
}
