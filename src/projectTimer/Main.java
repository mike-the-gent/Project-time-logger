package projectTimer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projectTimer.projectData.Project;
import projectTimer.projectData.ProjectData;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ProjectTimerUI.fxml"));
        primaryStage.setTitle("Project Timer");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) throws InterruptedException {

        launch(args);


    }

}
