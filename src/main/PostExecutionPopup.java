package main;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Matrix;
import model.Result;
import model.Vector;


/**
 * File: main.PostExecutionPopup.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class PostExecutionPopup {
    private Stage window;
    private Scene scene;
    private BorderPane main;
    private VBox infoStats;

    private int timesRan;
    private long avgRunTime;
    private boolean isParallel;
    private double[] result;
    private Boolean hasData = false;
    private Result data;

    /**
     *
     * @param isParallel
     */
    public PostExecutionPopup(boolean isParallel){
        this.isParallel = isParallel;
        this.init();
    }

    /**
     *
     */
    public void init() {
        // Init Panes
        main = new BorderPane();
        infoStats = new VBox(10);

        window = new Stage();
        scene = new Scene(main, 300, 300);
    }

    /**
     *
     */
    public void display() {
        Text runs = new Text("Times Ran: " + this.timesRan);
        Text avgTime = new Text("Avg Time per Run: " + this.avgRunTime);
        Text execution = isParallel ? new Text("Execution: parallel") : new Text("Execution: serial");
        if (this.result.length == 3){
            Text matrixStartTitle = new Text("Starting Matrix: ");
            Text matrixStart = new Text(Matrix.toString(this.data.getStartingMatrix()));
            Text vectorStartTitle = new Text("Starting Vector: ");
            Text vectorStart = new Text(Vector.toString(this.data.getStartingVector()));
            Text matrixEndTitle = new Text("Resulting Matrix: ");
            Text matrixEnd = new Text(Matrix.toString(this.data.getResultingMatrix()));
            Text vectorTitle = new Text("Resulting Vector: ");
            Text vector = new Text(Vector.toString(this.result));
            infoStats.getChildren().addAll(runs, avgTime, execution,
                    matrixStartTitle, matrixStart, vectorStartTitle,
                    vectorStart, matrixEndTitle, matrixEnd, vectorTitle, vector);
        }
        infoStats.getChildren().addAll(runs, avgTime, execution);
        main.setCenter(infoStats);
        scene.setRoot(main);
        window.setScene(scene);
        window.show();
    }

    /**
     *
     */
    public void blankDisplay(){
        Text loading = new Text("Running...");
        VBox tmp = new VBox(10);
        tmp.getChildren().add(loading);
        main.setCenter(tmp);
        scene.setRoot(main);
        window.setScene(scene);
        window.show();
    }

    /**
     *
     * @param timesRan
     * @param avgRunTime
     * @param vector
     */
    public void setData(int timesRan, long avgRunTime, double[] vector, Result data){
        this.timesRan = timesRan;
        this.avgRunTime = avgRunTime;
        this.result = vector;
        display();
    }
}
