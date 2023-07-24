package controller;

import model.suppliers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class suppliersController {
    private static final String JSON_FILE_PATH = System.getProperty("user.dir") + "/data/suppliers.json";

    // Method to view suppliers from JSON file
    public Object[][] viewFromJSON() {
        // read the JSON file and parse suppliers
        List<suppliers> suppliers = readFromJSON();
        // prepare the data for the table
        Object[][] data = new Object[suppliers.size()][5];
        for (int i = 0; i < suppliers.size(); i++) {
            suppliers supplier = suppliers.get(i);
            data[i][0] = supplier.getSpId();
            data[i][1] = supplier.getName();
            data[i][2] = supplier.getProfession();
            data[i][3] = supplier.getContact();
            data[i][4] = supplier.getEmail();
        }
        return data;
    }

    //read the JSON file and parse suppliers into a list
    private List<suppliers> readFromJSON() {
        List<suppliers> suppliers = new ArrayList<>();
        try {
            String json = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));
            json = json.replace("[", "").replace("]", ""); // Remove square brackets from the JSON string
            String[] supplierJsonArray = json.split("\\{"); // Split the JSON string by curly braces to separate individual supplier objects
            for (String supplierJson : supplierJsonArray) {
                if (!supplierJson.isEmpty()) {
                    String[] keyValuePairs = supplierJson.split(","); // Split the supplierJson by commas
                    int spId = 0;
                    String name = "";
                    String profession = "";
                    String contact = "";
                    String email = "";
                    boolean validSupplier = false; // Flag to check if the supplier is valid
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
                                    case "spId" -> spId = Integer.parseInt(value);
                                    case "name" -> name = value;
                                    case "profession" -> profession = value;
                                    case "contact" -> contact = value;
                                    case "email" -> {
                                        if (value.contains("}")) {
                                            value = value.replace("}", "");
                                        }
                                        email = value;
                                    }
                                }
                            }
                        }
                        // Check if the supplier is valid
                        if (spId > 0 && !name.isEmpty() && !profession.isEmpty() && !contact.isEmpty() && !email.isEmpty()) {
                            validSupplier = true;
                        }
                    }
                    // Add the supplier to the list if it is valid
                    if (validSupplier) {
                        suppliers.add(new suppliers(spId, name, profession, contact, email));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    return suppliers;
    }

    //addSupplier method with parameters to add a new supplier
    public void addToJSON(String name, String profession, String contact, String email) {
        // read the JSON file and parse suppliers
        List<suppliers> suppliers = readFromJSON();
        // generate a new supplier ID
        int spId = getLastSupplierId() + 1;
        //if spId is already in use, throw an exception to prevent duplicates
        for (suppliers supplier : suppliers) {
            if (supplier.getSpId() == spId) {
                throw new RuntimeException("Supplier ID already in use");
            }
        }
        // add the new supplier to the list
        suppliers.add(new suppliers(spId, name, profession, contact, email));
        saveToJSON(suppliers);
    }

    //saveToJSON method to save the updated list to the JSON file
    private void saveToJSON(List<suppliers> suppliers) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < suppliers.size(); i++) {
            suppliers supplier = suppliers.get(i);
            json.append("{");
            json.append("\"spId\": ").append(supplier.getSpId()).append(",");
            json.append("\"name\": \"").append(supplier.getName()).append("\",");
            json.append("\"profession\": \"").append(supplier.getProfession()).append("\",");
            json.append("\"contact\": \"").append(supplier.getContact()).append("\",");
            json.append("\"email\": \"").append(supplier.getEmail()).append("\"");
            json.append("}");
            if (i < suppliers.size() - 1) {
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

    //updateSupplier method with parameters to update an existing supplier
    public int deleteFromJSON(int deleteId) {
        List<suppliers> suppliers = readFromJSON();
        for (int i = 0; i < suppliers.size(); i++) {
            suppliers supplier = suppliers.get(i);
            if (supplier.getSpId() == deleteId) {
                suppliers.remove(i);
                saveToJSON(suppliers);
                return 1;
            }
        }
        saveToJSON(suppliers);
        return 0;
    }

    // get the highest supplier ID
    public int getLastSupplierId() {
        List<suppliers> suppliers = readFromJSON();
        int spId = 0;
        for (suppliers supplier : suppliers) {
            if (supplier.getSpId() > spId) {
                spId = supplier.getSpId();
            }
        }
        return spId + 1;
    }

    public void updateInJSON(int supplierId, String name, String profession, String contact, String email) {
        List<suppliers> suppliers = readFromJSON();
        for (int i = 0; i < suppliers.size(); i++) {
            suppliers supplier = suppliers.get(i);
            if (supplier.getSpId() == supplierId) {
                supplier.setName(name);
                supplier.setProfession(profession);
                supplier.setContact(contact);
                supplier.setEmail(email);
                saveToJSON(suppliers);
                return;
            }
        }
    }
}
