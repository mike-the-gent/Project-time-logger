package projectTimer;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class ProjectAccordionController extends TitledPane implements ActionListener {

    private final UUID id = UUID.randomUUID();

    private final ObjectProperty<EventHandler<ActionEvent>> onRemoveProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<EventHandler<ActionEvent>> onTimeOn = new SimpleObjectProperty<>();
    private final ObjectProperty<EventHandler<ActionEvent>> onTimeOff = new SimpleObjectProperty<>();


    @FXML
    private Text nameOfNewProject;
    @FXML
    private Text minutesSpentOnNewProject;
    @FXML
    private Text priceQuotedForNewProject;
    @FXML
    private Text hourlyRateCalculated;
    @FXML
    private Button removeButton;
    @FXML
    private Button timeOn;
    @FXML
    private Button timeOff;


    // The setters below are set in the Controller and revive information from the data.
    public void setNameOfNewProject(String nameOfNewProject) {
        this.nameOfNewProject.setText(nameOfNewProject);
    }

    public void setMinutesSpentOnNewProject(String minutesSpentOnNewProject) {
        this.minutesSpentOnNewProject.setText(minutesSpentOnNewProject);
    }

    public void setPriceQuotedForNewProject(String priceQuotedForNewProject) {
        this.priceQuotedForNewProject.setText(priceQuotedForNewProject);
    }

    public void setHourlyRateCalculated(String hourlyRateCalculated) {
        this.hourlyRateCalculated.setText(hourlyRateCalculated);
    }

    public ProjectAccordionController() {
        FXMLLoader fxmlLoader = new FXMLLoader(ProjectAccordionController.class.getResource("projectAccordionUI.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        removeButton.onActionProperty().bind(onRemoveProperty);
        timeOn.onActionProperty().bind(onTimeOn);
        timeOff.onActionProperty().bind(onTimeOff);
    }

    //     Setup the Time ON
    public void setOnTimeOn (EventHandler<ActionEvent> onTime) {
        this.onTimeOn.set(onTime);
    }
    //     Setup the Time OFF
    public void setOnTimeOff (EventHandler<ActionEvent> offTime) {
        this.onTimeOff.set(offTime);
    }



    public void setOnRemoveProperty(EventHandler<ActionEvent> onRemove) {
        this.onRemoveProperty.set(onRemove);
//        This is Set up only
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectAccordionController that = (ProjectAccordionController) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {

    }

}

