package libraries;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;

import util.Database;


public class Migrator {

    private final Connection connection;
    private final String migrationsTable = "schema_migrations";
    private final String sqlDirectory = "resources/db/migrations";

    public Migrator(Connection connection) {
        this.connection = connection;
    }

    public void migrate() throws Exception {
        ensureMigrationsTableExists();
        List<String> executedFiles = getExecutedFiles();
        List<Path> sqlFiles = getSqlFiles();

        for (Path sqlFile : sqlFiles) {
            String fileName = sqlFile.getFileName().toString();
            if (!executedFiles.contains(fileName)) {
                executeSqlFile(sqlFile);
                recordExecutedFile(fileName);
            }
        }
    }

    private void ensureMigrationsTableExists() throws SQLException {
        String createTableQuery = """
            CREATE TABLE IF NOT EXISTS %s (
                id INT AUTO_INCREMENT PRIMARY KEY,
                file_name VARCHAR(255) UNIQUE NOT NULL,
                executed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            """.formatted(migrationsTable);

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableQuery);
        }
    }

    private List<String> getExecutedFiles() throws SQLException {
        List<String> executedFiles = new ArrayList<>();
        String query = "SELECT file_name FROM " + migrationsTable;

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                executedFiles.add(rs.getString("file_name"));
            }
        }

        return executedFiles;
    }

    private List<Path> getSqlFiles() throws IOException {
        return Files.list(Paths.get(sqlDirectory))
                .filter(path -> path.toString().endsWith(".sql"))
                .sorted()
                .toList();
    }

    private void executeSqlFile(Path sqlFile) throws IOException, SQLException {
        String sql = Files.readString(sqlFile);

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println(sqlFile.getFileName()+"\t ------ \t ok ");
        }
    }

    private void recordExecutedFile(String fileName) throws SQLException {
        String insertQuery = "INSERT INTO " + migrationsTable + " (file_name) VALUES (?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, fileName);
            pstmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        try{
            
            Migrator migrator = new Migrator(Database.connect());
            migrator.migrate();
            System.out.println("Migration completed successfully!");
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}