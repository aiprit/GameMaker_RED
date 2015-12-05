package engine.front_end;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import structures.run.IParameters;

public class ObjectInformationView extends VBox {
    private Map<Text, Text> textList = new HashMap<>();
    private Map<Label, Slider> sliders = new HashMap<>();
    private Map<Label, Button> buttons = new HashMap<>();
    private GridPane myGrid = new GridPane();
    private int index = 0;
    private Map<String, Double> myOriginalDoubleMap;
    private Map<String, Boolean> myOriginalBooleanMap;
    private Map<String, String> myStringMap;
    private Map<String, Double> myDoubleMap;
    private Map<String, Boolean> myBooleanMap;
    private IParameters myParameterObject;

    @SuppressWarnings("unchecked")
    public ObjectInformationView(IParameters parameterObject) {
        myParameterObject = parameterObject;
        this.setWidth(500);
        this.setHeight(50);
        myGrid.setVgap(15);
        myGrid.getStyleClass().add("grid");
        this.getChildren().add(myGrid);
        myOriginalDoubleMap = (Map<String, Double>) ((TreeMap<String, Double>) myParameterObject.getDoubleMap()).clone();
        myOriginalBooleanMap = (Map<String, Boolean>) ((TreeMap<String, Boolean>) myParameterObject.getBooleanMap()).clone();
        populateStringParameters(myParameterObject.getStringMap());
        populateDoubleParameters(myParameterObject.getDoubleMap());
        populateBooleanParameters(myParameterObject.getBooleanMap());
        reset();
        close();
    }

    private void populateBooleanParameters(Map<String, Boolean> parameters) {
        if (parameters == null) return;
        myBooleanMap = parameters;
        for (String s : parameters.keySet()) {
            Label caption = new Label(s);
            Button button = new Button(String.valueOf(parameters.get(s)));
            button.setOnMouseClicked(e -> {
                boolean bool = Boolean.valueOf(button.getText());
                button.setText(String.valueOf(!bool));
                notifyObject();
            });
            GridPane.setConstraints(caption, 0, index);
            GridPane.setConstraints(button, 1, index++);
            buttons.put(caption, button);
            myGrid.getChildren().addAll(caption, button);
        }
    }

    private void populateDoubleParameters(Map<String, Double> parameters) {
        if (parameters == null) return;
        myDoubleMap = parameters;
        for (String s : parameters.keySet()) {
            Label caption = new Label(s);
            Slider slider = new Slider(0, 1, parameters.get(s));
            Label currentValue = new Label(String.format("%.0f", slider.getValue()*100) + "%");
            GridPane.setConstraints(caption, 0, index);
            GridPane.setConstraints(slider, 1, index);
            GridPane.setConstraints(currentValue, 2, index++);
            slider.setBlockIncrement(0);
            slider.valueProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue<? extends Number> ov,
                                    Number old_val, Number new_val) {
                    new_val = new_val.doubleValue() * 100;
                    currentValue.setText(String.format("%.0f", new_val) + "%");
                    notifyObject();
                }
            });
            sliders.put(caption, slider);
            myGrid.getChildren().addAll(caption, slider, currentValue);
        }
    }

    private void populateStringParameters(Map<String, String> parameters) {
        // We don't want strings to be editable, just shown
        if (parameters == null) return;
        myStringMap = parameters;
        for (String s : parameters.keySet()) {
            Text text = new Text(s);
            myGrid.add(text, 0, index);
            Text value = new Text(parameters.get(s));
            myGrid.add(value, 1, index++);
            textList.put(text, value);
        }
    }
    
    private void notifyObject() {
        for (Label label : sliders.keySet()) {
            myDoubleMap.put(label.getText(), sliders.get(label).getValue());
        }
        for (Label label : buttons.keySet()) {
            myBooleanMap.put(label.getText(), Boolean.valueOf(buttons.get(label).getText()));
        }
        for (Text textField : textList.keySet()) {
            myStringMap.put(textField.getText(), textList.get(textField).getText());
        }
        myParameterObject.setParameterMaps(myStringMap, myDoubleMap, myBooleanMap);
    }
    
    private void reset() {
        Button button = new Button ("Reset");
        button.setOnMouseClicked(e -> {
            for (Label label : sliders.keySet()) {
                sliders.get(label).setValue(myOriginalDoubleMap.get(label.getText()));
            }
            for (Label label : buttons.keySet()) {
                buttons.get(label).setText(String.valueOf(myOriginalBooleanMap.get(label.getText())));
            }
            notifyObject();
        });
        GridPane.setConstraints(button, 0, index);
        myGrid.getChildren().add(button);
    }
    
    private void close() {
        Button button = new Button ("Close");
        button.setOnMouseClicked(e -> {
            this.getChildren().clear();
        });
        GridPane.setConstraints(button, 1, index++);
        myGrid.getChildren().add(button);
    }

}
