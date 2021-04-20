package sample.utils;

import com.google.gson.JsonObject;
import sample.models.Departments;

public class DepartmentsRequests {
    private static final String URL = "http://localhost:8080/api/departments/";

    public static Departments parsedep(JsonObject department){
        Long depid = department.get("id").getAsLong();
        String depname = department.get("department_name").getAsString();
        return new Departments(depid, depname);
    }
}
