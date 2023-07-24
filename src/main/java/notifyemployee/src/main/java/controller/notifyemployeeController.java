package controller;

import model.notifyemployee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class notifyemployeeController {
    private static final String JSON_FILE_PATH = System.getProperty("user.dir") + "/data/employees.json";


    // Method to get only empid and email
    public Object[][] getFromJSON() {
        // Read the JSON file and parse employees
        List<notifyemployee> ordernotify = readFromJSON();

        // Prepare the data for the table
        Object[][] data = new Object[ordernotify.size()][3];
        for (int i = 0; i < ordernotify.size(); i++) {
            notifyemployee a = ordernotify.get(i);
            data[i][0] = a.getEmpId();
            data[i][1] = a.getProfession();
            data[i][2] = a.getEmail();
        }
        return data;
    }

    private List<notifyemployee> readFromJSON() {
        List<notifyemployee> ordernotify = new ArrayList<>();
        try {
            String json = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));

            // Extract individual employee objects without square brackets
            String[] employeeJsonArray = json.substring(1, json.length() - 1).split("\\{");

            for (String employeeJson : employeeJsonArray) {
                if (!employeeJson.isEmpty()) {
                    String[] keyValuePairs = employeeJson.split(",");

                    int empId = 0;
                    String profession = "";
                    String email = "";

                    for (String keyValue : keyValuePairs) {
                        String[] keyValueEntry = keyValue.split(":");

                        if (keyValueEntry.length == 2) {
                            String key = keyValueEntry[0].trim().replace("\"", "");
                            String value = keyValueEntry[1].trim().replace("\"", "");

                            switch (key) {
                                case "empId":
                                    empId = Integer.parseInt(value);
                                    break;
                                case "profession":
                                    profession = value;
                                    break;
                                case "email":
                                    email = value.replace("{", "").replace("}", "").trim();
                                    break;
                            }
                        }
                    }

                    // Add the employee to the list if it is valid
                    notifyemployee employee = new notifyemployee(empId, profession, email);
                    ordernotify.add(employee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ordernotify;
    }
}
