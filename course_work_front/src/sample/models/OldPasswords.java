package sample.models;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OldPasswords {
    private final LongProperty id;
    private final StringProperty oldPassword;

    public OldPasswords(){this(null);}

    public OldPasswords(String oldPassword){
        this.id = null;
        this.oldPassword = new SimpleStringProperty(oldPassword);
    }

    public OldPasswords(Long id, String oldPassword){
        this.id = new SimpleLongProperty(id);
        this.oldPassword = new SimpleStringProperty(oldPassword);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getOldPassword() {
        return oldPassword.get();
    }

    public StringProperty oldPasswordProperty() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword.set(oldPassword);
    }
}
