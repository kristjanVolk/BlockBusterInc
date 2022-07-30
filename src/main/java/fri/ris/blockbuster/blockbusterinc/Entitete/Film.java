package fri.ris.blockbuster.blockbusterinc.Entitete;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Film {
    private String imeFilma;
    private int id;
    private String opisFilma;
    private double cena;
    private String genre;
    private double ocenaFilma;
    public Film(int id, String imeFilma, String opisFilma, String genre, double cena, double ocenaFilma){
        this.id = id;
        this.imeFilma = imeFilma;
        this.opisFilma = opisFilma;
        this.genre = genre;
        this.cena = cena;
        this.ocenaFilma = ocenaFilma;
    }

    public String getFilm(){
        return this.id+" "+this.imeFilma+" "+this.opisFilma+" "+this.genre+" "+this.ocenaFilma+" "+this.cena;
    }

    public double getCena() {
        return this.cena;
    }

    public int getId() {
        return id;
    }

    public String getImeFilma() {
        return imeFilma;
    }

    public String getOpisFilma() {
        return opisFilma;
    }

    public String getGenre() {
        return genre;
    }

    public double getOcenaFilma() {
        return ocenaFilma;
    }
}
