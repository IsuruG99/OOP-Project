package controller;

import model.employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class employeeController {
    private static final String JSON_FILE_PATH = System.getProperty("user.dir") + "/data/employees.json";

    // Method to view employees from JSON file
    public Object[][] viewFromJSON() {
        // read the JSON file and parse employees
        List<employee> employees = readFromJSON();
        // prepare the data for the table
        Object[][] data = new Object[employees.size()][6];
        for (int i = 0; i < employees.size(); i++) {
            employee employee = employees.get(i);
            data[i][0] = employee.getEmpId();
            data[i][1] = employee.getName();
            data[i][2] = employee.getProfession();
            data[i][3] = employee.getContact();
            data[i][4] = employee.getEmail();
            data[i][5] = employee.getWorkStatus();
        }
        return data;
    }

    private List<employee> readFromJSON() {
        List<employee> employees = new ArrayList<>();
        try {
            String json = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));
            json = json.replace("[", "").replace("]", ""); // Remove square brackets from the JSON string
            String[] employeeJsonArray = json.split("\\{"); // Split the JSON string by curly braces to separate individual employee objects
            for (String employeeJson : employeeJsonArray) {
                if (!employeeJson.isEmpty()) {
                    String[] keyValuePairs = employeeJson.split(","); // Split the employeeJson by commas
                    int empId = 0;
                    String name = "";
                    String profession = "";
                    String contact = "";
                    String email = "";
                    String workStatus = "";
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
                                    case "name" -> name = value;
                                    case "profession" -> profession = value;
                                    case "contact" -> contact = value;
                                    case "email" -> email = value;
                                    case "workStatus" -> {
                                        //remove curly braces from the value and trim whitespace
                                        value = value.replace("{", "").replace("}", "").trim();
                                        workStatus = value;
                                    }
                                }
                            }
                        }
                        // Check if the employee is valid
                        if (empId > 0 && !name.isEmpty() && !profession.isEmpty() && !contact.isEmpty() && !email.isEmpty()) {
                            validEmployee = true;
                        }
                    }
                    // Add the employee to the list if it is valid
                    if (validEmployee) {
                        employee employee = new employee(empId, name, profession, contact, email, workStatus);
                        employees.add(employee);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // Add employee to JSON file
    public void addToJSON(String name, String profession, String contact, String email, String workStatus) {
        // generate the employee ID
        int empId = getHighestEmpId() + 1;
        // read the JSON file and parse employees
        List<employee> employees = readFromJSON();
        // add the new employee to the List from View
        employees.add(new employee(empId, name, profession, contact, email, workStatus));
        // save the updated list to JSON
        saveToJSON(employees);
    }

    // save to JSON
    private void saveToJSON(List<employee> employees) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < employees.size(); i++) {
            employee employee = employees.get(i);
            json.append("{")
                    .append("\"empId\":").append(employee.getEmpId()).append(",")
                    .append("\"name\":\"").append(employee.getName()).append("\",")
                    .append("\"profession\":\"").append(employee.getProfession()).append("\",")
                    .append("\"contact\":\"").append(employee.getContact()).append("\",")
                    .append("\"email\":\"").append(employee.getEmail()).append("\",")
                    .append("\"workStatus\":\"").append(employee.getWorkStatus()).append("\"")
                    .append("}")
                    .append("}");
            if (i < employees.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        try {
            Files.write(Paths.get(JSON_FILE_PATH), json.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Delete employee from JSON file
    public void deleteFromJSON(int empId) {
        // read the JSON file and parse employees
        List<employee> employees = readFromJSON();
        // delete the employee from the List from View
        for (int i = 0; i < employees.size(); i++) {
            employee employee = employees.get(i);
            if (employee.getEmpId() == empId) {
                employees.remove(i);
                break;
            }
        }
        // save the updated list to JSON
        saveToJSON(employees);
    }

    // get the highest employee ID
    public int getHighestEmpId() {
        // read the JSON file and parse employees
        List<employee> employees = readFromJSON();
        // get the highest employee ID
        int empId = 0;
        for (employee employee : employees) {
            if (employee.getEmpId() > empId) {
                empId = employee.getEmpId();
            }
        }
        return empId;
    }
}
