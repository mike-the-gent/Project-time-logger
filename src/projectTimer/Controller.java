package projectTimer;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import projectTimer.projectData.Project;
import projectTimer.projectData.ProjectData;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Optional;

public class Controller {


    public BorderPane mainPanel;
    private ProjectData data;

    @FXML
    private Accordion accordion;
    private Project tempProject;


    public void initialize() {
        data = new ProjectData();
        data.loadProjects();
        for (int i = 0; i < data.getProjects().size(); i++)
            addProjects(data.getProjects().get(i));
    }

    public void addProjects(Project addThisProject) {
        var projectAccordion = new ProjectAccordionController();

        // setting up the 'if we press delete'
        EventHandler<ActionEvent> removeEventHandler = event -> {
            // BELOW IS THE ORIGINAL TEXT YOU MADE TO SETUP THE REMOVE ACCORDION. THIS TEXT IS JUST FOR THE LOADED PROJECTS.
            // NOW THE DELETE METHOD DOES THIS JOB, BUT YOU KEPT IT IN CASE.

//            int indexOfProject = accordion.getPanes().indexOf(projectAccordion);
//            Project selectedProject = data.getProjects().get(indexOfProject);
//            data.deleteProject(selectedProject);
//            data.saveProjects();
//            accordion.getPanes().remove(projectAccordion);
//            tempProject = selectedProject;
            onDeleteAction();
        };

        // if TIME ON PRESSED
        EventHandler<ActionEvent> timeOnEventHandler = event -> {
            System.out.println(data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getName() + " : timeOn was Pressed");
            data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).setTimeOn(true);
            accordion.getExpandedPane().textProperty().set(data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getName() + " - NOW RECORDING TIME");
            accordion.getExpandedPane().textFillProperty().set(Color.RED);
            accordion.getExpandedPane().styleProperty().set("-fx-border-color:RED");
        };

        // if TIME OFF PRESSED
        EventHandler<ActionEvent> timeOffEventHandler = event -> {
            System.out.println(data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getName() + " : timeOff was Pressed");
            //                double minutesSpentBefore = data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getMinutesSpent();
            data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).setTimeOn(false);
            if (data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getNewlyWorkedTime() > 0.0) {
                System.out.println("You have just worked " + data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getNewlyWorkedTime() + " minutes");

                // If nothing is selected then an alert will appear. Below is the code for an Alert window with INFORMATION
                Alert addToProjectTime = new Alert(Alert.AlertType.CONFIRMATION);
                addToProjectTime.setTitle("Add to time?");
                addToProjectTime.setHeaderText(null);
                addToProjectTime.setContentText("Would you like to add " + data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getNewlyWorkedTime() + " minutes to your projects time?");
                addToProjectTime.showAndWait();
                if (addToProjectTime.getResult() == ButtonType.OK) {
                    data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).setMinutesSpent(data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getMinutesSpent() + data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getNewlyWorkedTime());
                    projectAccordion.setMinutesSpentOnNewProject(String.valueOf(data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getMinutesSpent()));
                    projectAccordion.setHourlyRateCalculated(String.valueOf(data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getHourlyWage()));
                    data.saveProjects();

                } else {
                    System.out.println("cancelled");
                }
                accordion.getExpandedPane().textProperty().set(data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getName());
                accordion.getExpandedPane().textFillProperty().set(Color.BLACK);
                accordion.getExpandedPane().styleProperty().set("-fx-border-color:none");

            }
//            System.out.println("no time was captured");
        };

        projectAccordion.setOnTimeOn(timeOnEventHandler);
        projectAccordion.setOnTimeOff(timeOffEventHandler);
        projectAccordion.setOnRemoveProperty(removeEventHandler);
        accordion.getPanes().add(projectAccordion);

        // add the title to the pane
        int indexOfProject = accordion.getPanes().indexOf(projectAccordion);
        projectAccordion.textProperty().set(data.getProjects().get(indexOfProject).getName());

        // add info to the internal parts. Use the Setters in ProjectAccordionController;
        projectAccordion.setNameOfNewProject(data.getProjects().get(indexOfProject).getName());
        projectAccordion.setMinutesSpentOnNewProject(String.valueOf(data.getProjects().get(indexOfProject).getMinutesSpent()));
        projectAccordion.setPriceQuotedForNewProject(String.valueOf(data.getProjects().get(indexOfProject).getQuotedPrice()));
        projectAccordion.setHourlyRateCalculated(String.valueOf(data.getProjects().get(indexOfProject).getHourlyWage()));


    }

    public void onAddAction() {

        var projectAccordion = new ProjectAccordionController();

        // setting up the 'if we press delete'
        EventHandler<ActionEvent> removeEventHandler = event -> {
            onDeleteAction();
        };
        projectAccordion.setOnRemoveProperty(removeEventHandler);

        if (accordion.getPanes().size() <= 0) {
            accordion.getPanes().add(0, projectAccordion);
        } else {
            accordion.getPanes().add(data.projectListIndex, projectAccordion);
        }


        // if TIME ON PRESSED
        EventHandler<ActionEvent> timeOnEventHandler = event -> {
            System.out.println(data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getName() + " : timeOn was Pressed");
            data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).setTimeOn(true);
            accordion.getExpandedPane().textProperty().set(data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getName() + " - NOW RECORDING TIME");
            accordion.getExpandedPane().textFillProperty().set(Color.RED);
            accordion.getExpandedPane().styleProperty().set("-fx-border-color:RED");
        };

        // if TIME OFF PRESSED
        EventHandler<ActionEvent> timeOffEventHandler = event -> {
            System.out.println(data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getName() + " : timeOff was Pressed");
            //                double minutesSpentBefore = data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getMinutesSpent();
            data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).setTimeOn(false);
            if (data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getNewlyWorkedTime() > 0.0) {
                System.out.println("You have just worked " + data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getNewlyWorkedTime() + " minutes");

                // If nothing is selected then an alert will appear. Below is the code for an Alert window with INFORMATION
                Alert addToProjectTime = new Alert(Alert.AlertType.CONFIRMATION);
                addToProjectTime.setTitle("Add to time?");
                addToProjectTime.setHeaderText(null);
                addToProjectTime.setContentText("Would you like to add " + data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getNewlyWorkedTime() + " minutes to your projects time?");
                addToProjectTime.showAndWait();
                if (addToProjectTime.getResult() == ButtonType.OK) {
                    data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).setMinutesSpent(data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getMinutesSpent() + data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getNewlyWorkedTime());
                    projectAccordion.setMinutesSpentOnNewProject(String.valueOf(data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getMinutesSpent()));
                    projectAccordion.setHourlyRateCalculated(String.valueOf(data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getHourlyWage()));
                    data.saveProjects();

                } else {
                    System.out.println("cancelled");
                }
                accordion.getExpandedPane().textProperty().set(data.getProjects().get(accordion.getPanes().indexOf(projectAccordion)).getName());
                accordion.getExpandedPane().textFillProperty().set(Color.BLACK);
                accordion.getExpandedPane().styleProperty().set("-fx-border-color:none");

            }
//            System.out.println("no time was captured");
        };

        projectAccordion.setOnTimeOn(timeOnEventHandler);
        projectAccordion.setOnTimeOff(timeOffEventHandler);
        projectAccordion.setOnRemoveProperty(removeEventHandler);
//        accordion.getPanes().add(projectAccordion);




        // add the title to the pane
        int indexOfProject = data.projectListIndex;
        projectAccordion.textProperty().set(data.getProjects().get(indexOfProject).getName());

//                     add info to the internal parts. Use the Setters in ProjectAccordionController;
        projectAccordion.setNameOfNewProject(data.getProjects().get(indexOfProject).getName());
        projectAccordion.setMinutesSpentOnNewProject(String.valueOf(data.getProjects().get(indexOfProject).getMinutesSpent()));
        projectAccordion.setPriceQuotedForNewProject(String.valueOf(data.getProjects().get(indexOfProject).getQuotedPrice()));
        projectAccordion.setHourlyRateCalculated(String.valueOf(data.getProjects().get(indexOfProject).getHourlyWage()));


    }

    public void showEditProjectDialog() {

        if (accordion.getExpandedPane() != null) {

            Dialog<ButtonType> dialog = new Dialog<ButtonType>();
            dialog.initOwner(mainPanel.getScene().getWindow());
            dialog.setTitle("Edit Project");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("NewProjectDialog.fxml"));
            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
                int indexOfProject = accordion.getPanes().indexOf(accordion.getExpandedPane());
                accordion.getExpandedPane().textProperty().set(data.getProjects().get(indexOfProject).getName());
                Project selectedProject = data.getProjects().get(indexOfProject);

//                     add info to the internal parts. Use the Setters in ProjectAccordionController;
                // ADD TEXT TO ELEMENTS HERE.
                NewProjectDialogController newProjectDialogController = fxmlLoader.getController();
                newProjectDialogController.editProject(selectedProject);

            } catch (IOException e) {
                System.out.println("Couldn't load the dialog");
                e.printStackTrace();
                return;
            }

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                // Process the information - Call the Contact Controller method
                NewProjectDialogController newProjectDialogController = fxmlLoader.getController();
                if (newProjectDialogController.getNewProject() != null){
                    Project newProject = newProjectDialogController.getNewProject();

                    //delete the old
                    int indexOfProject = accordion.getPanes().indexOf(accordion.getExpandedPane());
                    Project selectedProject = data.getProjects().get(indexOfProject);
                    tempProject = selectedProject;
                    data.deleteProject(selectedProject);
                    data.saveProjects();


                    // add the new
                    accordion.getPanes().remove(accordion.getExpandedPane());
                    data.addProject(newProject);
                    onAddAction();
                    data.saveProjects();
                } else {
                    System.out.println("edit project operation has been cancelled");
                }



            }
        } else {
            // If nothing is selected then an alert will appear. Below is the code for an Alert window with INFORMATION
            Alert delete = new Alert(Alert.AlertType.INFORMATION);
            delete.setTitle("No Project open");
            delete.setHeaderText(null);
            delete.setContentText("Please expand a project to enable editing.");
            delete.showAndWait();

        }
    }


    @FXML
    public void showAddProjectDialog() {

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Add New Project");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("NewProjectDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            // Process the information - Call the Contact Controller method
            NewProjectDialogController newProjectDialogController = fxmlLoader.getController();
            if (newProjectDialogController.getNewProject() != null) {
                Project newProject = newProjectDialogController.getNewProject();

                data.addProject(newProject);
                onAddAction();
                data.saveProjects();
            } else {
                System.out.println("add project operation has been cancelled");

            }
        }
    }


    @FXML
    public void onDeleteAction() {

        if (accordion.getExpandedPane() != null) {
            try {
                // Are you sure you want to delete?
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete?");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete?");
                alert.showAndWait();

                // If Ok is pressed...
                if (alert.getResult() == ButtonType.OK) {
                    int indexOfProject = accordion.getPanes().indexOf(accordion.getExpandedPane());
                    Project selectedProject = data.getProjects().get(indexOfProject);
                    tempProject = selectedProject;
                    data.deleteProject(selectedProject);
                    data.saveProjects();
                    accordion.getPanes().remove(accordion.getExpandedPane());

                    // add a info alert to tell me how I did on the project.

                    Alert alertDone = new Alert(Alert.AlertType.INFORMATION);
                    alertDone.setTitle("Done");
                    alertDone.setHeaderText(null);
                    alertDone.setContentText("Project ' " + tempProject.getName() + "' was deleted. You earned £ " + tempProject.getHourlyWage() + " per hour.");
                    alertDone.showAndWait();

                } else {
//                    Let me know cancel was pressed
//                    System.out.println("delete cancelled");
                }
            } catch (Exception e) {
                // If there is no expanded pane() to delete
                Alert delete = new Alert(Alert.AlertType.INFORMATION);
                delete.setTitle("No Project open");
                delete.setHeaderText(null);
                delete.setContentText("Please expand a project to enable deleting.");
                delete.showAndWait();
            }
        } else {
            // If there is no expanded pane() to edit
            Alert delete = new Alert(Alert.AlertType.INFORMATION);
            delete.setTitle("No Project open");
            delete.setHeaderText(null);
            delete.setContentText("Please expand a project to enable deleting.");
            delete.showAndWait();

        }
    }

    // For my use - print to find out array index positions.
    @FXML
    public void onPrint() {
        data.printProjectList();
        for (int i = 0; i < accordion.getPanes().size(); i++) {
            System.out.println("Accordion index = " + i);
        }

    }
}
