package engine.front_end;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ObjectInformationView extends VBox {
    private List<TextField> textList = new ArrayList<TextField>();
    private GridPane myGrid = new GridPane();
    private int index = 0;

    public ObjectInformationView() {
        this.setWidth(500);
        this.setHeight(50);
        Text scoreInfo = new Text("Object Name: ");
        this.getChildren().add(scoreInfo);
        Map<String, Boolean> map = new HashMap<>();
        myGrid.setVgap(15);
        this.getChildren().add(myGrid);
        map.put("Hello", true);
        map.put("You", false);
        map.put("Batman", false);
        map.put("Shia", true);
        map.put("Brenna", false);
        populateBooleanParameters(map);
    }

    public void setImage(String path) {
        final ImageView objectImage = new ImageView (path);
        GridPane.setConstraints(objectImage, 0, index);
        GridPane.setColumnSpan(objectImage, 3);
        this.getChildren().add(objectImage);
        index++;
    }

    public void populateBooleanParameters(Map<String, Boolean> parameters) {
        for (String s : parameters.keySet()) {
            Label caption = new Label(s);
            Button button = new Button(String.valueOf(parameters.get(s)));
            button.setOnMouseClicked(e -> {
                boolean bool = Boolean.valueOf(button.getText());
                button.setText(String.valueOf(!bool));
            });
            GridPane.setConstraints(caption, 0, index);
            GridPane.setConstraints(button, 1, index);
            myGrid.getChildren().addAll(caption, button);
            index++;
        }
    }

    public void populateDoubleParameters(Map<String, Double> parameters) {
        for (String s : parameters.keySet()) {
            Label caption = new Label(s);
            Slider slider = new Slider(0, 1, parameters.get(s));
            Label currentValue = new Label(Double.toString(slider.getValue()));
            GridPane.setConstraints(caption, 0, index);
            GridPane.setConstraints(slider, 1, index);
            GridPane.setConstraints(currentValue, 2, index);
            slider.valueProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue<? extends Number> ov,
                                    Number old_val, Number new_val) {
                    currentValue.setText(String.format("%.2f", new_val));
                }
            });
            myGrid.getChildren().addAll(caption, slider, currentValue);
            index++;
        }
    }

    public void populateStringParameters(Map<String, String> parameters) {
        for (String s : parameters.keySet()) {
            TextField inputField = new TextField();
            inputField.setText(parameters.get(s));
            inputField.setPromptText(s);
            Label parameterName = new Label(s);
            myGrid.add(parameterName, 0, index);
            myGrid.add(inputField, 1, index);
            textList.add(inputField);
            index++;
        }
    }

    public void clear() {
        this.getChildren().clear();
    }

}
