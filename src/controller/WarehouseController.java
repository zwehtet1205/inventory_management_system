package controller;

import java.util.List;
import model.Warehouse;

public class WarehouseController {

    // Get all warehouses
    public static List<Warehouse> getAllWarehouses() {
        return Warehouse.getAll(Warehouse.class); 
    }

    // Get specific warehouse by ID
    public static Warehouse get(int id) {
        return Warehouse.findOrFail(Warehouse.class, id); 
    }

    // Add a new warehouse
    public static boolean add(String name, int status_id, String location, int created_by) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(name);
        warehouse.setStatus_id(status_id);
        warehouse.setLocation(location);
        warehouse.setCreated_by(created_by);
        
        return warehouse.save();
    }

    // Update warehouse details
    public static boolean update(int id, String name, int status_id, String location) {
        Warehouse warehouse = Warehouse.findOrFail(Warehouse.class, id);
        warehouse.setName(name);
        warehouse.setStatus_id(status_id);
        warehouse.setLocation(location);
        
        return warehouse.save();
    }

    // Delete warehouse by ID
    public static boolean delete(int id) {
        Warehouse warehouse = Warehouse.findOrFail(Warehouse.class, id);
        
        return warehouse.delete();
    }

    // Check if warehouse exists
    public static boolean exist(int id) {
        return Warehouse.isExist("id", id, "warehouses");
    }
}
