package fri.ris.blockbuster.blockbusterinc.Entitete;

public class Stranka {
    private String ime;
    private String priimek;
    private String userName;
    private String ePosta;
    private double sredstva;

    public Stranka(String ime, String priimek, String userName, String ePosta){
        this.ime = ime;
        this.priimek = priimek;
        this.userName = userName;
        this.ePosta = ePosta;
        this.sredstva = 0;
    }

    public String getIme() {
        return ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public String getePosta() {
        return ePosta;
    }

    public double getSredstva(){
        return sredstva;
    }

    public String vrniOsebnePodatke(){
        return this.ime + " " + this.priimek + " " + this.ePosta + " " +this.userName;
    }

    public void setSredstva(double sredstva){
        this.sredstva = sredstva;
    }

    public String getUsername() {
        return this.userName;
    }
}
