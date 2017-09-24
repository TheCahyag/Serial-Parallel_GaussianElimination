import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Matrix;
import model.Result;

import java.util.ArrayList;

/**
 * File: ExecutionGUI.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class ExecutionGUI extends Application {
    /* GUI Nodes */
    private Stage window;
    private BorderPane main;
    private HBox parallelSerialSwitch;
    private VBox dimensionRadioButtons, controlPanel;
    private Button execute;
    private ToggleGroup parallelSerial, matrixDimension;
    private RadioButton parallel, serial,
            sixtyFour, twoFiftySix, tenTwentyFour, fortyNinetySix, sixTeenThreeEightyFour;
    private Spinner<Integer> repeatRuns, minBounds, maxBounds;

    @Override
    public void init() throws Exception {
        main = new BorderPane();

        // HBoxes
        parallelSerialSwitch = new HBox();

        // VBoxes
        dimensionRadioButtons = new VBox(10, new Text("Select Matrix Size:"));
        controlPanel = new VBox(10);

        // Buttons
        execute = new Button("Execute");
        setButtonAction();

        // Spinners
        repeatRuns = new Spinner<>(0, 30, 5);
        minBounds = new Spinner<>(Integer.MIN_VALUE, Integer.MAX_VALUE - 1, Matrix.DEFAULT_LOW);
        maxBounds = new Spinner<>(Integer.MIN_VALUE + 1, Integer.MAX_VALUE, Matrix.DEFAULT_HIGH);

        // ToggleGroup
        parallelSerial = new ToggleGroup();
        matrixDimension = new ToggleGroup();

        // Radio Buttons for parallelSerial
        parallel = new RadioButton("Parallel");
        serial = new RadioButton("Serial");
        parallel.setToggleGroup(parallelSerial);
        serial.setToggleGroup(parallelSerial);
        /* Parallel and serial have booleans used when
        calling the selected button in the toggle group */
        parallel.setUserData(true);
        serial.setUserData(false);
        parallel.setSelected(true);

        // Radio Buttons for matrixDimension
        sixtyFour = new RadioButton("64");
        twoFiftySix = new RadioButton("256");
        tenTwentyFour = new RadioButton("1024");
        fortyNinetySix = new RadioButton("4096");
        sixTeenThreeEightyFour = new RadioButton("16384");
        sixtyFour.setToggleGroup(matrixDimension);
        twoFiftySix.setToggleGroup(matrixDimension);
        tenTwentyFour.setToggleGroup(matrixDimension);
        fortyNinetySix.setToggleGroup(matrixDimension);
        sixTeenThreeEightyFour.setToggleGroup(matrixDimension);
        sixtyFour.setUserData(64);
        twoFiftySix.setUserData(256);
        tenTwentyFour.setUserData(1024);
        fortyNinetySix.setUserData(4096);
        sixTeenThreeEightyFour.setUserData(16384);
        sixtyFour.setSelected(true);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = new Stage();

        // Add Nodes to Panes
        parallelSerialSwitch.getChildren().addAll(parallel, serial);
        dimensionRadioButtons.getChildren().addAll(sixtyFour, twoFiftySix, tenTwentyFour, fortyNinetySix, sixTeenThreeEightyFour);
        controlPanel.getChildren().addAll(parallelSerialSwitch,
                new Text("Times to run:"), repeatRuns,
                new Text("Min value for matrix:"), minBounds,
                new Text("Max value for matrix:"), maxBounds,
                execute);

        main.setRight(controlPanel);
        main.setLeft(dimensionRadioButtons);
        Scene scene = new Scene(main, 500, 500);
        window.setScene(scene);
        window.show();

    }

    /**
     * Sets the actions to preform when a given button is clicked.
     */
    private void setButtonAction(){
        execute.setOnAction(event -> executeProblem());
    }

    /**
     * Gather parameters from the radio buttons and execute the
     * {@link GaussianElimination} problem with those parameters.
     */
    private void executeProblem(){
        int dim = (int) matrixDimension.getSelectedToggle().getUserData();
        boolean isParallel = (boolean) parallelSerial.getSelectedToggle().getUserData();
        int timesToRun = repeatRuns.getValue();
        if (isParallel){
            // Parallel execution
            long avgTime = 0;
            for (int i = 0; i < timesToRun; i++) {
                Result result = GaussianElimination.parallelExecution(dim, minBounds.getValue(), maxBounds.getValue());
                avgTime = ((avgTime * i) + result.getTimeInMilli()) / (i + 1);
            }
            new PostExecutionPopup(timesToRun, avgTime, true, null);

        } else {
            // Serial execution
            long avgTime = 0;
            for (int i = 0; i < timesToRun; i++) {
                Result result = GaussianElimination.serialExecution(dim, minBounds.getValue(), maxBounds.getValue());
                avgTime = ((avgTime * i) + result.getTimeInMilli()) / (i + 1);
            }
            new PostExecutionPopup(timesToRun, avgTime, false, null);
        }
    }
}
