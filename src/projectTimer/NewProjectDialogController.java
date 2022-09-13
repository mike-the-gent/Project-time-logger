package projectTimer;

import com.sun.javafx.scene.control.DoubleField;
import com.sun.javafx.scene.control.IntegerField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import projectTimer.projectData.Project;

public class NewProjectDialogController {
    public TextField projectNameField;
    public TextField minutesSpentField;
    public TextField quotedPriceField;
    public TextField notesField;

    public Project getNewProject() {

        try {

            String projectName = projectNameField.getText();
            double minutesSpent = Double.parseDouble(minutesSpentField.getText());
            double priceQuoted = Double.parseDouble(quotedPriceField.getText());
//        String notes = notesField.getText();

            Project newProject = new Project(projectName, minutesSpent, priceQuoted);
            return newProject;

        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("incorrect input format");
            error.setHeaderText(null);
            error.setContentText("Please ensure your fields are \n"+"\n" +
                    "Name of project should be of input type :    TEXT \n" +
                    "Minutes Spent should be of input type :    NUMBER \n" +
                    "Price Quoted should be of input type :    NUMBER");
            error.showAndWait();
        } return null;

    }




    public void editProject (Project project) {
        projectNameField.setText(project.getName());
        minutesSpentField.setText(toString().valueOf(project.getMinutesSpent()));
        quotedPriceField.setText(toString().valueOf(project.getQuotedPrice()));
    }


}
