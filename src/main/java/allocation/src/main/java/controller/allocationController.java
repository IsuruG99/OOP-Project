package controller;

import model.allocation;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class allocationController {

    private static final String JSON_FILE_PATH = System.getProperty("user.dir") + "/data/allocation.json";

    // view all orders from JSON
    public Object[][] viewFromJSON() {
        List<allocation> allocate = readFromJSON();
        // Prepare the data for the table
        Object[][] data = new Object[allocate.size()][4];
        for (int i = 0; i < allocate.size(); i++) {
            allocation alloc = allocate.get(i);
            data[i][0] = alloc.getOrderID();
            data[i][1] = alloc.getEmpID();
            data[i][2] = alloc.getEmpEmail();
            data[i][3] = alloc.getCustomerEmail();
        }
        return data;
    }

    // read from JSON file
    public List<allocation> readFromJSON() {
        List<allocation> allocate = new ArrayList<>();
        try{
            String json = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));
            json = json.replace("[", "").replace("]", ""); // Remove square brackets from the JSON string
            String[] allocJsonArray = json.split("\\{"); // Split the JSON string by curly braces to separate individual order objects
            for (String allocJson : allocJsonArray) {
                if (!allocJson.isEmpty()) {
                    String[] keyValuePairs = allocJson.split(","); // Split the orderJson by commas
                    int orderID = 0;
                    int empID = 0;
                    String empEmail = "";
                    String customerEmail = "";
                    boolean validOrder = false; // Flag to check if the order is valid
                    if (keyValuePairs.length > 0) { // Skip the first empty element
                        int startIndex = 0;
                        if (keyValuePairs[0].trim().isEmpty()) {
                            startIndex = 1;
                        }
                        for (int i = startIndex; i < keyValuePairs.length; i++) {
                            String[] pair = keyValuePairs[i].split(":"); // Split each key-value pair by colon
                            if (pair.length == 2) {
                                String key = pair[0].trim().replace("\"", ""); // Remove quotes from the key
                                String value = pair[1].trim().replace("\"", ""); // Remove quotes from the value
                                switch (key) {
                                    case "orderID" -> orderID = Integer.parseInt(value);
                                    case "empID" -> empID = Integer.parseInt(value);
                                    case "empEmail" -> empEmail = value;
                                    case "customerEmail" -> {
                                        if (value.contains("}")){
                                            value = value.replace("}", "");
                                        }
                                        customerEmail = value;
                                    }
                                }
                            }
                            // Check if all the values are not empty
                            if (orderID != 0 && empID != 0 && !empEmail.isEmpty() && !customerEmail.isEmpty()) {
                                validOrder = true;
                            }
                        }
                        if (validOrder) {
                            allocate.add(new allocation(orderID, empID, empEmail, customerEmail));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allocate;
    }

    public int allocateOrder(int orderID, int empID, String empEmail, String customerEmail) {
        List<allocation> allocate = readFromJSON();
        allocate.add(new allocation(orderID, empID, empEmail, customerEmail));
        saveToJson(allocate);
        return 1;
    }

    private void saveToJson(List<allocation> allocate) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < allocate.size(); i++) {
            allocation alloc = allocate.get(i);
            json.append("{");
            json.append("\"orderID\": ").append(alloc.getOrderID()).append(",");
            json.append("\"empID\": ").append(alloc.getEmpID()).append(",");
            json.append("\"empEmail\": \"").append(alloc.getEmpEmail()).append("\",");
            json.append("\"customerEmail\": \"").append(alloc.getCustomerEmail()).append("\"");
            json.append("}");
            if (i < allocate.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        try {
            Files.write(Paths.get(JSON_FILE_PATH), json.toString().getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // delete order from JSON file
    public int deallocateOrder(int orderID, int empID) {
        List<allocation> allocate = readFromJSON();
        int valid = 0;
        for (allocation alloc : allocate) {
            if (alloc.getOrderID() == orderID && alloc.getEmpID() == empID) {
                valid = 1;
                allocate.remove(alloc);
                break;
            }
        }
        if (valid == 1) {
            saveToJson(allocate);
        }
        return valid;
    }
}
