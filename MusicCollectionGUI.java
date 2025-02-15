package EndtermProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicCollectionGUI {
    private JFrame frame;
    private JTable collectionTable, albumTable, trackTable;
    private DefaultTableModel collectionModel, albumModel, trackModel;
    private MusicCollectionApp2 app;

    public MusicCollectionGUI(MusicCollectionApp2 app) {
        this.app = app;

        frame = new JFrame("Music Collection Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        collectionModel = new DefaultTableModel(new String[]{"ID", "Name"}, 0);
        collectionTable = new JTable(collectionModel);
        tabbedPane.addTab("Collections", new JScrollPane(collectionTable));

        albumModel = new DefaultTableModel(new String[]{"ID", "Name", "Artist", "Genre", "Year", "Collection ID"}, 0);
        albumTable = new JTable(albumModel);
        tabbedPane.addTab("Albums", new JScrollPane(albumTable));

        trackModel = new DefaultTableModel(new String[]{"ID", "Name", "Duration", "Author", "Album ID"}, 0);
        trackTable = new JTable(trackModel);
        tabbedPane.addTab("Tracks", new JScrollPane(trackTable));

        frame.add(tabbedPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 6, 5, 5));
        String[] buttonNames = {"Add Collection", "Update Collection", "Delete Collection", "Show Collections",
                "Add Album", "Update Album", "Delete Album", "Show Albums",
                "Add Track", "Update Track", "Delete Track", "Show Tracks"};

        for (String name : buttonNames) {
            JButton button = new JButton(name);
            buttonPanel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (name) {
                        case "Add Collection" -> app.addCollection();
                        case "Update Collection" -> app.updateCollection();
                        case "Delete Collection" -> app.deleteCollection();
                        case "Show Collections" -> app.showCollections();
                        case "Add Album" -> app.addAlbum();
                        case "Update Album" -> app.updateAlbum();
                        case "Delete Album" -> app.deleteAlbum();
                        case "Show Albums" -> app.showAlbums();
                        case "Add Track" -> app.addTrack();
                        case "Update Track" -> app.updateTrack();
                        case "Delete Track" -> app.deleteTrack();
                        case "Show Tracks" -> app.showTracks();
                    }
                }
            });
        }

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public int getCollectionId() {
        return getSelectedId(collectionTable, collectionModel);
    }

    public int getAlbumId() {
        return getSelectedId(albumTable, albumModel);
    }

    public int getTrackId() {
        return getSelectedId(trackTable, trackModel);
    }

    private int getSelectedId(JTable table, DefaultTableModel model) {
        int row = table.getSelectedRow();
        if (row == -1) return -1;
        return (int) model.getValueAt(row, 0);
    }

    public void setTableData(String tableName, String[] columns, Object[][] data) {
        DefaultTableModel model = switch (tableName) {
            case "Collections" -> collectionModel;
            case "Albums" -> albumModel;
            default -> trackModel;
        };
        model.setRowCount(0);
        for (Object[] row : data) {
            model.addRow(row);
        }
    }
}