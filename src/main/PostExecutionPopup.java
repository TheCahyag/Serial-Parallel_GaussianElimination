package main;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


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
        scene = new Scene(main, 300, 500);
    }

    /**
     *
     */
    public void display() {
        Text runs = new Text("Times Ran: " + this.timesRan);
        Text avgTime = new Text("Avg Time per Run: " + this.avgRunTime);
        Text execution = isParallel ? new Text("Execution: parallel") : new Text("Execution: serial");
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
    public void setData(int timesRan, long avgRunTime, double[] vector){
        this.timesRan = timesRan;
        this.avgRunTime = avgRunTime;
        this.result = vector;
        System.out.println("new data: " + avgRunTime);
        display();
    }
}
