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
    public static boolean addWarehouse(String name,  String location,int status_id, int created_by) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(name);
        warehouse.setStatus_id(status_id);
        warehouse.setLocation(location);
        warehouse.setCreated_by(created_by);
        warehouse.setCreated_at(new java.util.Date());
        warehouse.setUpdated_at(new java.util.Date());
        
        return warehouse.add(warehouse);
    }

    // Update warehouse details
    public static boolean updateWarehouse(int id, String name,  String location , int status_id) {
        Warehouse warehouse = Warehouse.findOrFail(Warehouse.class, id);
        warehouse.setName(name);
        warehouse.setStatus_id(status_id);
        warehouse.setLocation(location);
        warehouse.setUpdated_at(new java.util.Date());
        
        return warehouse.update(warehouse);
    }

    // Delete warehouse by ID
    public static boolean delete(int id) {
        Warehouse warehouse = Warehouse.findOrFail(Warehouse.class, id);
        
        return warehouse.delete(warehouse);
    }

    // Check if warehouse exists
    public static boolean exist(int id) {
        return Warehouse.isExist("id", id, "warehouses");
    }
}
