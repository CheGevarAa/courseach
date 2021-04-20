package sample.models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Report {
    private final ObjectProperty<LocalDate> creation_date;
    private final StringProperty employee;
    private final LongProperty department_id;
    private final LongProperty  danger_level;
    private final LongProperty error;

    public Report(){this(null, null, null, null, null);}

    public Report(LocalDate creation_date, String employee, Long department_id, Long danger_level, Long error){
        this.creation_date = new SimpleObjectProperty<>(creation_date);
        this.employee = new SimpleStringProperty(employee);
        this.department_id = new SimpleLongProperty(department_id);
        this.danger_level = new SimpleLongProperty(danger_level);
        this.error = new SimpleLongProperty(error);
    }

    public LocalDate getCreation_date() { return creation_date.get(); }

    public ObjectProperty<LocalDate> creation_dateProperty() { return creation_date; }

    public void setCreation_date(LocalDate creation_date) { this.creation_date.set(creation_date); }

    public String getEmployee() { return employee.get(); }

    public StringProperty employeeProperty() { return employee; }

    public void setEmployee(String employee) { this.employee.set(employee); }

    public long getDepartment_id() { return department_id.get(); }

    public LongProperty department_idProperty() { return department_id; }

    public void setDepartment_id(long department_id) { this.department_id.set(department_id); }

    public long getDanger_level() { return danger_level.get(); }

    public LongProperty danger_levelProperty() { return danger_level; }

    public void setDanger_level(long danger_level) { this.danger_level.set(danger_level); }

    public long getError() { return error.get(); }

    public LongProperty errorProperty() { return error; }

    public void setError(long error) { this.error.set(error); }
}
