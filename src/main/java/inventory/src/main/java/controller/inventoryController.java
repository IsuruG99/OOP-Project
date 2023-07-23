package controller;

import model.inventory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class inventoryController {

    // path to inventory.json
    private static final String JSON_FILE_PATH = System.getProperty("user.dir") + "/data/inventory.json";

    // Get all inventory from JSON file
    public Object[][] viewFromJSON() {
        // Read the JSON file and parse inventory
        List<inventory> inventory = readFromJSON();
        // Prepare the data for the table
        Object[][] data = new Object[inventory.size()][3];
        for (int i = 0; i < inventory.size(); i++) {
            inventory ivn = inventory.get(i);
            data[i][0] = ivn.getItemID();
            data[i][1] = ivn.getItemName();
            data[i][2] = ivn.getItemPrice();
        }
        return data;
    }

    // Read inventory from JSON file
    private List<inventory> readFromJSON() {
        List<inventory> inventory = new ArrayList<>();
        try{
            String json = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));
            json = json.replace("[", "").replace("]", ""); // Remove square brackets from the JSON string
            String[] inventoryJsonArray = json.split("\\{"); // Split the JSON string by curly braces to separate individual inventory objects
            for (String inventoryJson : inventoryJsonArray) {
                if (!inventoryJson.isEmpty()) {
                    String[] keyValuePairs = inventoryJson.split(","); // Split the inventoryJson by commas

                    String itemID = "";
                    String itemName = "";
                    String itemPrice = "";
                    boolean validInventory = false; // Flag to check if the inventory is valid
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
                                    case "itemID" -> itemID = value;
                                    case "name" -> itemName = value;
                                    case "price" -> {
                                        //remove closing brackets and trim whitespace
                                        value = value.replace("}", "").trim();
                                        itemPrice = value;
                                    }
                                }
                            }
                            if (!itemID.isEmpty() && !itemName.isEmpty() && !itemPrice.isEmpty()) {
                                validInventory = true;
                            }
                        }
                        if (validInventory) {
                            inventory.add(new inventory(itemID, itemName, itemPrice));
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //if inventory is empty, add a default item
        if (inventory.isEmpty()) {
            inventory.add(new inventory("1", "Default Item", "0.00"));
        }
        return inventory;
    }


}
