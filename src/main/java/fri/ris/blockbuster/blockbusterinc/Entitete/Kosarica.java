package fri.ris.blockbuster.blockbusterinc.Entitete;

import java.util.HashMap;

public class Kosarica {
    private int kolicinaFilmov;
    private double skupnaCena;
    public HashMap<Film,Integer> filmi;
    public  Kosarica(){
        this.filmi = getFilmi();
        this.skupnaCena = vrniSkupnoCeno();
        this.kolicinaFilmov = getKolicinaFilmov();
    }
    public Kosarica(HashMap<Film,Integer> filmi){
        this.filmi = filmi;
        this.skupnaCena = vrniSkupnoCeno();
        this.kolicinaFilmov = getKolicinaFilmov();
    }

    public double vrniSkupnoCeno() {
        this.skupnaCena = 0;
        for(Film film :  this.filmi.keySet()){
            this.skupnaCena += (film.getCena() * this.filmi.get(film));
        }
        return this.skupnaCena;
    }


    public int getKolicinaFilmov(){
        int stFilmov = 0;
        for(int value : this.filmi.values()){
            stFilmov += value;
        }
        return stFilmov;
    }

    public HashMap<Film, Integer> getFilmi() {
        return this.filmi;
    }

    public void setKosarica(HashMap<Film, Integer> kos) {
        this.filmi = kos;
    }
}
