package controller;

import model.Newordernotify;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class NewordernotifyController {
    private static final String JSON_FILE_PATH = System.getProperty("user.dir") + "/data/employees.json";

    //method to get only empid and email
    public Object[][] getEmpIdAndEmail() {
        // read the JSON file and parse employees
        List<Newordernotify> ordernotify = readFromJSON();
        // prepare the data for the table
        Object[][] data = new Object[ordernotify.size()][2];
        for (int i = 0; i < ordernotify.size(); i++) {
            Newordernotify Nordernotify = ordernotify.get(i);
            data[i][0] = Nordernotify.getEmpId();
            data[i][1] = Nordernotify.getEmail();
        }
        return data;

}

    private List<Newordernotify> readfromJSON(){
        List<Newordernotify> ordernotify = new ArrayList<>();
        try {
            String json = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));
            json = json.replace("[", "").replace("]", ""); // Remove square brackets from the JSON string
            String[] employeeJsonArray = json.split("\\{"); // Split the JSON string by curly braces to separate individual employee objects
            for (String employeeJson : employeeJsonArray) {
                if (!employeeJson.isEmpty()) {
                    String[] keyValuePairs = employeeJson.split(","); // Split the employeeJson by commas
                    int empId = 0;
                    String profession = "";
                    String email = "";
                    boolean validEmployee = false; // Flag to check if the employee is valid
                    if (keyValuePairs.length > 0) { // Skip the first empty element
                        int startIndex = 0;
                        if (keyValuePairs[0].trim().isEmpty()) {
                            startIndex = 1;
                        }
                        for (int i = startIndex; i < keyValuePairs.length; i++) {
                            String[] keyValue = keyValuePairs[i].split(":"); // Split each key-value pair by colon
                            if (keyValue.length == 2) {
                                String key = keyValue[0].trim().replace("\"", ""); // Remove quotes from the key
                                String value = keyValue[1].trim().replace("\"", ""); // Remove quotes from the value
                                switch (key) {
                                    case "empId" -> empId = Integer.parseInt(value);
                                    case "profession" -> profession = value;
                                    case "email" -> {
                                        value = value.replace("{", "").replace("}", "").trim();
                                        email = value;
                                    }
                                }
                            }
                        }
                    }
                    // Add the employee to the list if it is valid
                    Newordernotify a = new Newordernotify(empId, profession, email);
                    ordernotify.add(a);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ordernotify;
}
