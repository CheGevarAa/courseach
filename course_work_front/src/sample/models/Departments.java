package sample.models;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Departments {
    private  final LongProperty id;
    private final StringProperty department_name;

    public Departments(){ this(null); }

    public Departments(String department_name){
        this.id = null;
        this.department_name = new SimpleStringProperty(department_name);
    }

    public Departments(Long id, String department_name){
        this.id = new SimpleLongProperty(id);
        this.department_name = new SimpleStringProperty(department_name);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getDepartment_name() {
        return department_name.get();
    }

    public StringProperty department_nameProperty() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name.set(department_name);
    }
}
