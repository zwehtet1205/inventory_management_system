package libraries;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;

import util.Database;

public abstract class SystemModel<T> {

    private static Connection con;
    private final Class<T> clazz;
    private final String tableName;
    private static final List<String> conditions = new ArrayList<>();
    private static final List<Object> parameters = new ArrayList<>();

    // Constructor to initialize the generic class and table name
    public SystemModel(Class<T> clazz, String tableName) {
        this.clazz = clazz;
        this.tableName = tableName;
    }

    // Open a connection to the database
    public static boolean openConnection() {
        try {
            if (con == null || con.isClosed()) {
                con = Database.connect();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Close the database connection
    public static boolean closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get the current connection object
    public static Connection getConnection() {
        return con;
    }

    // Generic method to fetch all records from the table
    public static <T> List<T> getAll(Class<T> clazz) {
        List<T> list = new ArrayList<>();
        String tableName = Pluralizer.getTableName(clazz);
        String sql = "SELECT * FROM " + tableName;

        try {
            if (!openConnection()) {
                throw new SQLException("Failed to open database connection");
            }

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                // Fetch fields of the class
                Field[] fields = clazz.getDeclaredFields();
                Constructor<T> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);

                // Iterate through the result set
                while (rs.next()) {
                    T obj = constructor.newInstance();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        String fieldName = field.getName();
                        try {
                            Object value = rs.getObject(fieldName);
                            field.set(obj, value);
                        } catch (SQLException ignored) {
                            // Ignore if the field does not match the column
                        }
                    }
                    list.add(obj);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }
    
    // Static method to fetch one record by ID
    public static <T> T findOrFail(Class<T> clazz, Object id) {
        T obj = null;
        String tableName = Pluralizer.getTableName(clazz);
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";

        try {
            if (!openConnection()) {
                throw new SQLException("Failed to open database connection");
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setObject(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Create a new instance of the class
                        Field[] fields = clazz.getDeclaredFields();
                        Constructor<T> constructor = clazz.getDeclaredConstructor();
                        constructor.setAccessible(true);
                        obj = constructor.newInstance();

                        // Set values for each field in the object
                        for (Field field : fields) {
                            field.setAccessible(true);
                            String fieldName = field.getName();
                            try {
                                Object value = rs.getObject(fieldName);
                                field.set(obj, value);
                            } catch (SQLException ignored) {
                                // Ignore if the field does not match the column
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return obj;
    }
    
 // Static method to fetch one record by ID
    public static <T> T findOrFail(Class<T> clazz,String column, Object val) {
        T obj = null;
        String tableName = Pluralizer.getTableName(clazz);
        String sql = "SELECT * FROM " + tableName + " WHERE " + column + " = ?";

        try {
            if (!openConnection()) {
                throw new SQLException("Failed to open database connection");
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setObject(1, val);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Create a new instance of the class
                        Field[] fields = clazz.getDeclaredFields();
                        Constructor<T> constructor = clazz.getDeclaredConstructor();
                        constructor.setAccessible(true);
                        obj = constructor.newInstance();

                        // Set values for each field in the object
                        for (Field field : fields) {
                            field.setAccessible(true);
                            String fieldName = field.getName();
                            try {
                                Object value = rs.getObject(fieldName);
                                field.set(obj, value);
                            } catch (SQLException ignored) {
                                // Ignore if the field does not match the column
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return obj;
    }
    
    // Static method to add a where condition
    public static <T> SystemModel<T> where(String column, String operator, Object value) {
        conditions.add(column + " " + operator + " ?");
        parameters.add(value);
        return null; // Return null to allow method chaining
    }
    
    // Static method to add a whereIn condition
    public static <T> SystemModel<T> whereIn(String column, List<?> values) {
        if (values.isEmpty()) return null;

        String placeholders = String.join(",", values.stream().map(v -> "?").toArray(String[]::new));
        conditions.add(column + " IN (" + placeholders + ")");
        parameters.addAll(values);
        return null; // Return null to allow method chaining
    }
    
    // Static method to fetch records with applied conditions
    public static <T> List<T> get(Class<T> clazz) {
        List<T> list = new ArrayList<>();
        String tableName = Pluralizer.getTableName(clazz);
        StringBuilder sql = new StringBuilder("SELECT * FROM " + tableName);

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        try {
            if (!openConnection()) {
                throw new SQLException("Failed to open database connection");
            }

            try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
                for (int i = 0; i < parameters.size(); i++) {
                    ps.setObject(i + 1, parameters.get(i));
                }

                try (ResultSet rs = ps.executeQuery()) {
                    Field[] fields = clazz.getDeclaredFields();
                    Constructor<T> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);

                    while (rs.next()) {
                        T obj = constructor.newInstance();
                        for (Field field : fields) {
                            field.setAccessible(true);
                            String fieldName = field.getName();
                            try {
                                Object value = rs.getObject(fieldName);
                                field.set(obj, value);
                            } catch (SQLException ignored) {
                                // Ignore if the field does not match the column
                            }
                        }
                        list.add(obj);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        conditions.clear(); // Clear conditions after the query
        parameters.clear(); // Clear parameters after the query
        return list;
    }
    
    // Static method to fetch a related record (belongsTo relationship)
    public static <R> Optional<R> belongsTo(Class<R> relatedClass, String relatedTable, String foreignKey, Object foreignKeyValue) {
        Optional<R> relatedObject = Optional.empty();
        String sql = "SELECT * FROM " + relatedTable + " WHERE id = ?";

        try {
            if (!openConnection()) {
                throw new SQLException("Failed to open database connection");
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setObject(1, foreignKeyValue);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Fetch fields of the related class
                        Field[] fields = relatedClass.getDeclaredFields();
                        Constructor<R> constructor = relatedClass.getDeclaredConstructor();
                        constructor.setAccessible(true);
                        R obj = constructor.newInstance();

                        // Set values for each field in the related object
                        for (Field field : fields) {
                            field.setAccessible(true);
                            String fieldName = field.getName();
                            try {
                                Object value = rs.getObject(fieldName);
                                field.set(obj, value);
                            } catch (SQLException ignored) {
                                // Ignore if the field does not match the column
                            }
                        }

                        relatedObject = Optional.of(obj);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return relatedObject;
    }
    
    // Static method to check if a record exists with the given conditions
    public static boolean isExist(String column, Object value,String tableName) {
        boolean exists = false;
        String sql = "SELECT 1 FROM " + tableName + " WHERE " + column + " = ? LIMIT 1";

        try {
            if (!openConnection()) {
                throw new Exception("Failed to open database connection");
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setObject(1, value);

                try (ResultSet rs = ps.executeQuery()) {
                    exists = rs.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return exists;
    }
    
    public static boolean isExist(String column, Object value, String tableName, Object id) {
        boolean exists = false;
        String sql = "SELECT 1 FROM " + tableName + " WHERE " + column + " = ?";

        if (id != null) {
            sql += " AND id <> ?";
        }

        sql += " LIMIT 1";

        try {
            if (!openConnection()) {
                throw new Exception("Failed to open database connection");
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setObject(1, value);
                if (id != null) {
                    ps.setObject(2, id);
                }

                try (ResultSet rs = ps.executeQuery()) {
                    exists = rs.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return exists;
    }


    // Static method to save an object (insert or update)
    public boolean save() {
        boolean isSuccess = false;

        try {
            if (!openConnection()) {
                throw new SQLException("Failed to open database connection");
            }

            Map<String, Object> fieldMap = new HashMap<>();
            Object primaryKeyValue = null;

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(this); // Using 'this' to refer to the current instance

                if ("id".equalsIgnoreCase(fieldName)) {
                    primaryKeyValue = value;
                } else {
                    fieldMap.put(fieldName, value);
                }
            }

            // Insert or Update logic
            if (primaryKeyValue != null) {
                StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
                for (String key : fieldMap.keySet()) {
                    sql.append(key).append(" = ?, ");
                }
                sql.setLength(sql.length() - 2); // Remove trailing comma
                sql.append(" WHERE id = ?");

                try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
                    int index = 1;

                    for (Object value : fieldMap.values()) {
                        ps.setObject(index++, value);
                    }
                    ps.setObject(index, primaryKeyValue);

                    isSuccess = ps.executeUpdate() > 0;
                }
            } else {
                StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
                StringBuilder placeholders = new StringBuilder();

                for (String key : fieldMap.keySet()) {
                    sql.append(key).append(", ");
                    placeholders.append("?, ");
                }
                sql.setLength(sql.length() - 2);
                placeholders.setLength(placeholders.length() - 2);
                sql.append(") VALUES (").append(placeholders).append(")");

                try (PreparedStatement ps = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS)) {
                    int index = 1;

                    for (Object value : fieldMap.values()) {
                        ps.setObject(index++, value);
                    }

                    isSuccess = ps.executeUpdate() > 0;

                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            Field idField = clazz.getDeclaredField("id");
                            idField.setAccessible(true);
                            idField.set(this, rs.getObject(1)); // Set the ID for the current object
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return isSuccess;
    }
    
    public boolean add(T obj) {
        boolean isSuccess = false;

        try {
            if (!openConnection()) {
                throw new Exception("Failed to open database connection");
            }

            StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
            StringBuilder placeholders = new StringBuilder();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();

                if (!"id".equalsIgnoreCase(fieldName)) { // Exclude primary key if auto-incremented
                    sql.append(fieldName).append(", ");
                    placeholders.append("?, ");
                }
            }

            sql.setLength(sql.length() - 2); // Remove trailing comma
            placeholders.setLength(placeholders.length() - 2); // Remove trailing comma
            sql.append(") VALUES (").append(placeholders).append(")");

            try (PreparedStatement ps = con.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS)) {
                int index = 1;

                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();

                    if (!"id".equalsIgnoreCase(fieldName)) { // Exclude primary key
                        Object value = field.get(obj);
                        ps.setObject(index++, value);
                    }
                }

                isSuccess = ps.executeUpdate() > 0;

                // Handle auto-generated ID
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        try {
                        	Field idField = clazz.getDeclaredField("id");
                        	idField.setAccessible(true);

                        	Object generatedId = rs.getObject(1);
                        	if (generatedId instanceof BigInteger) {
                        	    idField.set(obj, ((BigInteger) generatedId).intValue());
                        	} else if (generatedId instanceof Number) {
                        	    idField.set(obj, ((Number) generatedId).intValue());
                        	} else {
                        	    idField.set(obj, generatedId);
                        	}

                        } catch (NoSuchFieldException e) {
                            System.out.println("No 'id' field found in class " + clazz.getSimpleName());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return isSuccess;
    }

    
    // Dynamic update method to update data
    public boolean update(T obj) {
        boolean isSuccess = false;

        try {
            if (!openConnection()) {
                throw new Exception("Failed to open database connection");
            }

            StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
            Field[] fields = clazz.getDeclaredFields();
            Object primaryKeyValue = null;

            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();

                if ("id".equalsIgnoreCase(fieldName)) {
                    primaryKeyValue = field.get(obj); // Store the primary key value
                } else {
                    sql.append(fieldName).append(" = ?, ");
                }
            }

            sql.setLength(sql.length() - 2); // Remove trailing comma
            sql.append(" WHERE id = ?");

            if (primaryKeyValue == null) {
                throw new Exception("Primary key (id) value is null. Cannot update record.");
            }

            try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
                int index = 1;

                // Set values for all fields except the primary key
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();

                    if (!"id".equalsIgnoreCase(fieldName)) {
                        Object value = field.get(obj);
                        ps.setObject(index++, value);
                    }
                }

                // Set the primary key value
                ps.setObject(index, primaryKeyValue);

                isSuccess = ps.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return isSuccess;
    }


    
 // Delete method based on an object's non-null fields
    public boolean delete(T obj) {
        boolean isSuccess = false;

        try {
            if (!openConnection()) {
                throw new Exception("Failed to open database connection");
            }

            StringBuilder sql = new StringBuilder("DELETE FROM " + tableName + " WHERE ");
            Field[] fields = clazz.getDeclaredFields();

            boolean hasConditions = false;
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(obj);

                if (value != null) { // Only include non-null fields in the WHERE clause
                    sql.append(field.getName()).append(" = ? AND ");
                    hasConditions = true;
                }
            }

            if (!hasConditions) {
                throw new Exception("No conditions provided. Deleting all records is not allowed.");
            }

            sql.setLength(sql.length() - 5); // Remove trailing " AND "

            try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
                int index = 1;
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object value = field.get(obj);

                    if (value != null) { // Only bind non-null fields
                        ps.setObject(index++, value);
                    }
                }

                isSuccess = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return isSuccess;
    }
    
	public Class<T> getClazz() {
		return clazz;
	}

	public String getTableName() {
		return tableName;
	}
	
    
    
}
