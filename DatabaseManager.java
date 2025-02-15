package EndtermProject;

// DatabaseManager.java (управление базой данных)
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection conn;

    public DatabaseManager() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/music_collection", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCollection(String name) {
        executeUpdate("INSERT INTO collections (name) VALUES (?)", name);
    }

    public void updateCollection(int id, String name) {
        executeUpdate("UPDATE collections SET name = ? WHERE id = ?", name, id);
    }

    public void deleteCollection(int id) {
        executeUpdate("DELETE FROM collections WHERE id = ?", id);
    }

    public Object[][] getCollections() {
        return executeQuery("SELECT * FROM collections");
    }

    public void addAlbum(String name, String artist, String genre, int year, int collectionId) {
        executeUpdate("INSERT INTO albums (name, artist, genre, year, collection_id) VALUES (?, ?, ?, ?, ?)", name, artist, genre, year, collectionId);
    }

    public void updateAlbum(int id, String name) {
        executeUpdate("UPDATE albums SET name = ? WHERE id = ?", name, id);
    }

    public void deleteAlbum(int id) {
        executeUpdate("DELETE FROM albums WHERE id = ?", id);
    }

    public Object[][] getAlbums() {
        return executeQuery("SELECT * FROM albums");
    }

    public void addTrack(String name, int time, int albumId, String author) {
        executeUpdate("INSERT INTO tracks (name, time, id_album, author) VALUES (?, ?, ?, ?)", name, time, albumId, author);
    }

    public void updateTrack(int id, String name) {
        executeUpdate("UPDATE tracks SET name = ? WHERE id = ?", name, id);
    }

    public void deleteTrack(int id) {
        executeUpdate("DELETE FROM tracks WHERE id = ?", id);
    }

    public Object[][] getTracks() {
        return executeQuery("SELECT * FROM tracks");
    }

    private void executeUpdate(String query, Object... params) {
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Object[][] executeQuery(String query) {
        List<Object[]> dataList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                dataList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList.toArray(new Object[0][]);
    }
}



