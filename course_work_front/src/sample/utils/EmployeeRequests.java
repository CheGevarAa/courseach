package sample.utils;


import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import sample.models.Departments;
import sample.models.Employee;
import com.google.gson.JsonObject;
import sample.models.OldPasswords;

public class EmployeeRequests {
    private static final String URL = "http://localhost:8080/api/employees/";

    public static Employee getByUsername(String username){
        String conn = HTTPConnection.GetRequest(URL + "usernames/" + username);
        if(!conn.equals("null")){
            JsonObject result = new JsonParser().parse(conn).getAsJsonObject();
            Long empid = result.get("id").getAsLong();
            String empname = result.get("username").getAsString();
            String password = result.get("password").getAsString();
            JsonObject dep = result.get("department").getAsJsonObject();
            Departments departments = DepartmentsRequests.parsedep(dep);
            JsonElement oldpass = result.get("oldPasswords");
            OldPasswords passold = (oldpass instanceof JsonNull)
                    ? new OldPasswords()
                    : OldPasswordsRequests.parsepass(oldpass.getAsJsonObject());
            return new  Employee(empid, empname, password, departments, passold);
        }
        else{
            return null;
        }
    }
}
