import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Vector;

/**
 * File: PostExecutionPopup.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class PostExecutionPopup {
    private Stage window;
    private BorderPane main;
    private VBox infoStats;

    private int timesRan;
    private long avgRunTime;
    private boolean isParallel;
    private Vector result;

    /**
     * TODO
     * @param timesRan
     * @param avgRunTime
     * @param isParallel
     * @param result
     */
    public PostExecutionPopup(int timesRan, long avgRunTime, boolean isParallel, Vector result){
        this.timesRan = timesRan;
        this.avgRunTime = avgRunTime;
        this.isParallel = isParallel;
        this.result = result;
        this.init();
    }

    /**
     *
     */
    public void init() {
        // Init Panes
        main = new BorderPane();
        infoStats = new VBox(10);
    }

    /**
     *
     */
    public void display() {
        window = new Stage();

        Text runs = new Text("Times Ran: " + this.timesRan);
        Text avgTime = new Text("Avg Time per Run: " + this.avgRunTime);
        Text execution = isParallel ? new Text("Execution: parallel") : new Text("Execution: serial");
        infoStats.getChildren().addAll(runs, avgTime, execution);
        main.setCenter(infoStats);
        Scene scene = new Scene(main, 300, 500);
        window.setScene(scene);
        window.show();
    }
}
