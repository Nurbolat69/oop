package EndtermProject;

import java.util.ArrayList;
import java.util.List;

class Collection {
    private int id;
    private String name;
    private List<MusicalMedia> albums;

    public Collection(int id, String name) {
        this.id = id;
        this.name = name;
        this.albums = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<MusicalMedia> getAlbums() { return albums; }

    public void addAlbum(MusicalMedia album) {
        albums.add(album);
    }
}

class MusicalMedia {
    private int id;
    private String name;
    private int collectionId;
    private List<MusicalWork> tracks;

    public MusicalMedia(int id, String name, int collectionId) {
        this.id = id;
        this.name = name;
        this.collectionId = collectionId;
        this.tracks = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getCollectionId() { return collectionId; }
    public List<MusicalWork> getTracks() { return tracks; }

    public void addTrack(MusicalWork track) {
        tracks.add(track);
    }
}

class MusicalWork {
    private int id;
    private String name;
    private int duration;
    private int albumId;

    public MusicalWork(int id, String name, int duration, int albumId) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.albumId = albumId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getDuration() { return duration; }
    public int getAlbumId() { return albumId; }
}
