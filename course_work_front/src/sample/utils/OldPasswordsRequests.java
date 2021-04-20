package sample.utils;

import com.google.gson.JsonObject;
import sample.models.Departments;
import sample.models.OldPasswords;

public class OldPasswordsRequests {
    private static final String URL = "http://localhost:8080/api/oldPasswords/";

    public static OldPasswords parsepass(JsonObject oldpassword){
        Long passid = oldpassword.get("id").getAsLong();
        String passold = oldpassword.get("old_pass").getAsString();
        return new OldPasswords(passid, passold);
    }
}
