import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Metric Converter");

        // Labels
        Label inputLabel = new Label("Value:");
        Label fromUnitLabel = new Label("From Unit:");
        Label toUnitLabel = new Label("To Unit:");
        Label resultLabel = new Label("Result:");

        // Text Fields
        TextField valueInput = new TextField();
        TextField resultOutput = new TextField();
        resultOutput.setEditable(false);

        // ComboBoxes for units
        ComboBox<String> fromUnitDropdown = new ComboBox<>();
        ComboBox<String> toUnitDropdown = new ComboBox<>();

        // Populate the dropdowns with supported units
        fromUnitDropdown.getItems().addAll("kg", "g", "km", "mm");
        toUnitDropdown.getItems().addAll("lb", "oz", "mile", "inch");

        // Button
        Button convertButton = new Button("Convert");

        // Layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Adding elements to layout
        gridPane.add(inputLabel, 0, 0);
        gridPane.add(valueInput, 1, 0);
        gridPane.add(fromUnitLabel, 0, 1);
        gridPane.add(fromUnitDropdown, 1, 1);
        gridPane.add(toUnitLabel, 0, 2);
        gridPane.add(toUnitDropdown, 1, 2);
        gridPane.add(convertButton, 1, 3);
        gridPane.add(resultLabel, 0, 4);
        gridPane.add(resultOutput, 1, 4);

        // Event Handling
        convertButton.setOnAction(event -> {
            try {
                double value = Double.parseDouble(valueInput.getText());
                String fromUnit = fromUnitDropdown.getValue();
                String toUnit = toUnitDropdown.getValue();

                // Ensure both units are selected
                if (fromUnit == null || toUnit == null) {
                    resultOutput.setText("Please select both units");
                    return;
                }

                double result = convert(value, fromUnit, toUnit);

                if (result == -1) {
                    resultOutput.setText("Conversion not supported");
                } else {
                    resultOutput.setText(String.format("%.2f", result));
                }
            } catch (NumberFormatException e) {
                resultOutput.setText("Invalid input value");
            }
        });

        // Scene and Stage
        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static double convert(double value, String fromUnit, String toUnit) {
        if (fromUnit.equals("kg") && toUnit.equals("lb")) {
            return value * 2.20462;
        } else if (fromUnit.equals("g") && toUnit.equals("oz")) {
            return value * 0.035274;
        } else if (fromUnit.equals("km") && toUnit.equals("mile")) {
            return value * 0.621371;
        } else if (fromUnit.equals("mm") && toUnit.equals("inch")) {
            return value * 0.0393701;
        } else {
            return -1; 
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
