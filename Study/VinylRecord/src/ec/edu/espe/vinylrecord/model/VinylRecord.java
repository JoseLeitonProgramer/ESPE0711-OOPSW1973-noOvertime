package ec.edu.espe.vinylrecord.model;

/**
 *
 * @author Jose Leiton
 */
public class VinylRecord {
    private int id;
    private String tittle;
    private String artist;
    private int year;

    public VinylRecord(int id, String tittle, String artist, int year) {
        this.id = id;
        this.tittle = tittle;
        this.artist = artist;
        this.year = year;
    }

    @Override
    public String toString() {
        return "VinylRecord{" + "id=" + id + ", tittle=" + tittle + ", artist=" + artist + ", year=" + year + '}';
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tittle
     */
    public String getTittle() {
        return tittle;
    }

    /**
     * @param tittle the tittle to set
     */
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    /**
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }
    
    
}
