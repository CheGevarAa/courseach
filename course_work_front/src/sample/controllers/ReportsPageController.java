package sample.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Employee;
import sample.models.Report;
import sample.utils.ReportRequests;

import java.io.IOException;
import java.time.LocalDate;

public class ReportsPageController {
    /**
     * Модуль контроллера страницы приложения, содержащей данные об отчётах
     * В данном модуле прописано программное заполнение объектов формы необходимыми данными
     * при помощью метода инициализации
     */
    @FXML
    private TableView<Report> rep_table;

    @FXML
    private TableColumn<Report, LocalDate> rep_date;

    @FXML
    private TableColumn<Report, String> rep_creator;

    @FXML
    private TableColumn<Report, String> rep_dep;

    @FXML
    private TableColumn<Report, String> rep_level;

    @FXML
    private  TableColumn<Report, Number> rep_error;

    @FXML
    private Button newButton;

    private Main mainApp;
    private Stage stage;
    private ObservableList<Report> reports;
    private Employee employee;

    MainController controller = new MainController();

    public void initialize(Stage stage, ObservableList<Report> reports, Employee employee){
        this.stage=stage;
        this.reports=reports;
        this.employee=employee;
        rep_table.setItems(reports);
        rep_date.setCellValueFactory(cellData -> cellData.getValue().creation_dateProperty());
        rep_creator.setCellValueFactory(cellData -> cellData.getValue().employeeProperty().get().usernameProperty());
        rep_dep.setCellValueFactory(cellData -> cellData.getValue().employeeProperty().get().departmentProperty().get().department_nameProperty());
        rep_level.setCellValueFactory(cellData -> cellData.getValue().danger_levelProperty().get().descriptionProperty());
        rep_error.setCellValueFactory(cellData -> cellData.getValue().errorProperty().get().error_codeProperty());
        if (this.employee == null){
            newButton.setDisable(true);
        }
    }

    @FXML
    private void handleNewReport() throws IOException {
        Report tempReport = new Report();
        Report resReport = controller.showReportEditPage(stage, tempReport, employee);
        if(resReport != null){
            reports.add(resReport);
            ReportRequests.createReport(resReport);

        }
    }

    @FXML
    private void handleEditReport() throws IOException {
        Report tempReport = rep_table.getSelectionModel().getSelectedItem();
        Report resReport = controller.showReportEditPage(stage, tempReport, employee);
        if(resReport != null) {
            ReportRequests.updateReport(resReport);
        }
    }

    public void handleDeleteReport(){
        int selected = rep_table.getSelectionModel().getSelectedIndex();
        if(selected>=0){
            Boolean res = ReportRequests.deleteReport(rep_table.getSelectionModel().getSelectedItem());
            if(res){
                rep_table.getItems().remove(selected);
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(stage);
                alert.setTitle("Error");
                alert.setHeaderText("Could not delete this report");
                alert.setContentText("Try again");
                alert.showAndWait();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(stage);
            alert.setTitle("Error");
            alert.setHeaderText("Nothing to delete");
            alert.setContentText("Select object to delete");
            alert.showAndWait();
        }
    }
}
