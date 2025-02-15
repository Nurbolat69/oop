package EndtermProject;

//0сновное приложение
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicCollectionApp2 {
    private DatabaseManager dbManager;
    private MusicCollectionGUI gui;

    public MusicCollectionApp2() {
        dbManager = new DatabaseManager();
        gui = new MusicCollectionGUI(this);
    }

    public void addCollection() {
        String name = JOptionPane.showInputDialog("Enter collection name:");
        if (name == null || name.trim().isEmpty()) return;

        dbManager.addCollection(name);
        showCollections();
    }

    public void updateCollection() {
        int id = gui.getCollectionId();
        if (id == -1) return;

        String newName = JOptionPane.showInputDialog("Enter new collection name:");
        if (newName == null || newName.trim().isEmpty()) return;

        dbManager.updateCollection(id, newName);
        showCollections();
    }
    public void showCollections() {
        Object[][] data = dbManager.getCollections();
        gui.setTableData("Collections", new String[]{"ID", "Name"}, data);
    }


    public void deleteCollection() {
        int id = gui.getCollectionId();
        if (id == -1) return;
        dbManager.deleteCollection(id);
        showCollections();
    }

    public void addAlbum() {
        String name = JOptionPane.showInputDialog("Enter album name:");
        if (name == null || name.trim().isEmpty()) return;

        String artist = JOptionPane.showInputDialog("Enter artist name:");
        if (artist == null || artist.trim().isEmpty()) return;

        String genre = JOptionPane.showInputDialog("Enter genre:");
        if (genre == null || genre.trim().isEmpty()) return;

        int year = Integer.parseInt(JOptionPane.showInputDialog("Enter year:"));
        int collectionId = Integer.parseInt(JOptionPane.showInputDialog("Enter collection ID:"));

        dbManager.addAlbum(name, artist, genre, year, collectionId);
        showAlbums();
    }

    public void updateAlbum() {
        int id = gui.getAlbumId();
        if (id == -1) return;

        String name = JOptionPane.showInputDialog("Enter new album name:");
        if (name == null || name.trim().isEmpty()) return;

        dbManager.updateAlbum(id, name);
        showAlbums();
    }
    public void showAlbums() {
        Object[][] data = dbManager.getAlbums();
        gui.setTableData("Albums", new String[]{"ID", "Name", "Artist", "Genre", "Year", "Collection ID"}, data);
    }

    public void deleteAlbum() {
        int id = gui.getAlbumId();
        if (id == -1) return;
        dbManager.deleteAlbum(id);
        showAlbums();
    }

    public void addTrack() {
        String name = JOptionPane.showInputDialog("Enter song name:");
        if (name == null || name.trim().isEmpty()) return;

        int duration = Integer.parseInt(JOptionPane.showInputDialog("Enter duration in seconds:"));
        int albumId = Integer.parseInt(JOptionPane.showInputDialog("Enter album ID:"));
        String author = JOptionPane.showInputDialog("Enter author name:");
        if (author == null || author.trim().isEmpty()) return;

        dbManager.addTrack(name, duration, albumId, author);
        showTracks();
    }

    public void showTracks() {
        Object[][] data = dbManager.getTracks();
        gui.setTableData("Tracks", new String[]{"ID", "Name", "Duration", "Album ID"}, data);
    }

    public void updateTrack() {
        int id = gui.getTrackId();
        if (id == -1) return;

        String name = JOptionPane.showInputDialog("Enter new track name:");
        if (name == null || name.trim().isEmpty()) return;

        dbManager.updateTrack(id, name);
        showTracks();
    }
    public void deleteTrack() {
        int id = gui.getTrackId();
        if (id == -1) return;
        dbManager.deleteTrack(id);
        showTracks();
    }

    public static void main(String[] args) {
        new MusicCollectionApp2();
    }
}