package authoring_environment.FileHandlers;

import javafx.collections.ObservableList;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import structures.data.DataSound;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.util.Optional;
import java.util.ResourceBundle;

public class SoundMaker {
    private static ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");

    public static void load(Stage s, ObservableList<DataSound> sounds) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        File selectedFile = fileChooser.showOpenDialog(s);


        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(selectedFile);
            AudioFileFormat fileType = AudioSystem.getAudioFileFormat(selectedFile);

            String name = askName();

            File outputfile = new File(r.getString("soundsFolder") + name + ".wav");

            if (AudioSystem.isFileTypeSupported(fileType.getType(),
                    audioInputStream)) {

                AudioSystem.write(audioInputStream, fileType.getType(), outputfile);
            }
            DataSound sound = new DataSound(name, outputfile.getName());
            sounds.add(sound);

        } catch (Exception e) {
//		  // Handle the error...

        }


    }

    private static String askName() {
        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter your name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else
            return "IMAGE";


    }
}
