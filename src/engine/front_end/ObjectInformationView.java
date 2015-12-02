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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import structures.run.IParameters;

public class ObjectInformationView extends VBox {
    private List<TextField> textList = new ArrayList<TextField>();
    private Map<Label, Slider> sliders = new HashMap<>();
    private Map<Label, Button> buttons = new HashMap<>();
    private GridPane myGrid = new GridPane();
    private int index = 0;
    private Map<String, String> myStringMap;
    private Map<String, Double> myDoubleMap;
    private Map<String, Boolean> myBooleanMap;
    private IParameters myParameterObject;
    private ImageView myImage;

    public ObjectInformationView(IParameters parameterObject) {
        myParameterObject = parameterObject;
        this.setWidth(500);
        this.setHeight(50);
        myGrid.setVgap(15);
        this.getChildren().add(myGrid);
        setImage(parameterObject.getImage());
        populateStringParameters(myParameterObject.getStringMap());
        populateDoubleParameters(myParameterObject.getDoubleMap());
        populateBooleanParameters(myParameterObject.getBooleanMap());
        save();
    }

    private void setImage(Image image) {
        myImage = new ImageView(image);
        GridPane.setConstraints(myImage, 0, index++);
        GridPane.setColumnSpan(myImage, 3);
        this.getChildren().add(myImage);
    }

    private void populateBooleanParameters(Map<String, Boolean> parameters) {
        myBooleanMap = parameters;
        for (String s : parameters.keySet()) {
            Label caption = new Label(s);
            Button button = new Button(String.valueOf(parameters.get(s)));
            button.setOnMouseClicked(e -> {
                boolean bool = Boolean.valueOf(button.getText());
                button.setText(String.valueOf(!bool));
            });
            GridPane.setConstraints(caption, 0, index);
            GridPane.setConstraints(button, 1, index++);
            buttons.put(caption, button);
            myGrid.getChildren().addAll(caption, button);
        }
    }

    private void populateDoubleParameters(Map<String, Double> parameters) {
        myDoubleMap = parameters;
        for (String s : parameters.keySet()) {
            Label caption = new Label(s);
            Slider slider = new Slider(0, 1, parameters.get(s));
            Label currentValue = new Label(String.format("%.0f", slider.getValue()*100) + "%");
            GridPane.setConstraints(caption, 0, index);
            GridPane.setConstraints(slider, 1, index);
            GridPane.setConstraints(currentValue, 2, index++);
            slider.valueProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue<? extends Number> ov,
                                    Number old_val, Number new_val) {
                    new_val = new_val.doubleValue() * 100;
                    currentValue.setText(String.format("%.0f", new_val) + "%");
                }
            });
            sliders.put(caption, slider);
            myGrid.getChildren().addAll(caption, slider, currentValue);
        }
    }

    private void populateStringParameters(Map<String, String> parameters) {
        myStringMap = parameters;
        for (String s : parameters.keySet()) {
            TextField inputField = new TextField();
            inputField.setText(parameters.get(s));
            inputField.setPromptText(s);
            Label parameterName = new Label(s);
            myGrid.add(parameterName, 0, index);
            myGrid.add(inputField, 1, index++);
            textList.add(inputField);
        }
    }
    
    private void pullNewMapValues() {
        for (TextField textField : textList) {
            myStringMap.put(textField.getPromptText(), textField.getText());
        }
        for (Label label : sliders.keySet()) {
            myDoubleMap.put(label.getText(), sliders.get(label).getValue());
        }
        for (Label label : buttons.keySet()) {
            myBooleanMap.put(label.getText(), Boolean.valueOf(buttons.get(label).getText()));
        }
        myParameterObject.setParameterMaps(myStringMap, myDoubleMap, myBooleanMap);
    }
    
    private void save() {
        Button button = new Button ("Save");
        button.setOnMouseClicked(e -> {
            pullNewMapValues();
        });
        GridPane.setConstraints(button, 1, ++index);
        myGrid.getChildren().add(button);
        index++;
    }

    public void clear() {
        myStringMap.clear();
        myDoubleMap.clear();
        myBooleanMap.clear();
        this.getChildren().clear();
    }

}
