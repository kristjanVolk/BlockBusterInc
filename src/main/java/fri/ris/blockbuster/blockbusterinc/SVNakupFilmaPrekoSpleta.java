package fri.ris.blockbuster.blockbusterinc;

import fri.ris.blockbuster.blockbusterinc.Entitete.Film;
import fri.ris.blockbuster.blockbusterinc.Entitete.Kosarica;
import fri.ris.blockbuster.blockbusterinc.Entitete.Popust;
import fri.ris.blockbuster.blockbusterinc.Entitete.Stranka;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

public class SVNakupFilmaPrekoSpleta {
    public TextField imeRacun;
    public TextField priimekRacun;
    public TextField epostaRacun;
    public TextField addressNaslov;
    public TextField postaNslov;
    public Label prikazRacun;
    public Label stFilmovKoncniRacun;
    public Label skupnaCenaKoncniRacun;
    private KKupiFilmPrekoSpleta kKupiFilmPrekoSpleta;
    private static Kosarica kosarica = KKupiFilmPrekoSpleta.kosarica;
    private HashMap<Film,Integer> filmi;
    private Popust popust;
    private double cena, novaCena;
    public void initialize() {
        filmi = this.kosarica.getFilmi();
        this.popust = new Popust();
        this.kKupiFilmPrekoSpleta = new KKupiFilmPrekoSpleta();
        System.out.println(this.kKupiFilmPrekoSpleta.stranka.getSredstva()+"TOLKU IMM GWOREJ");
        for(Film film : filmi.keySet()){
            prikazRacun.setText(prikazRacun.getText()+" Ime: "+film.getImeFilma()+", Cena: "+film.getCena()+"€,"+" Kolicina: "+filmi.get(film)+" \n");
        }
        stFilmovKoncniRacun.setText("Število stvari v košarici: "+this.kosarica.getKolicinaFilmov());
        cena = this.kosarica.vrniSkupnoCeno();
        if(this.kosarica.getKolicinaFilmov() > 5 || this.kosarica.vrniSkupnoCeno() >= 50.0){
            System.out.println(this.kosarica.vrniSkupnoCeno() * this.popust.getPopust());
            novaCena = this.kosarica.vrniSkupnoCeno() - (this.kosarica.vrniSkupnoCeno() * this.popust.getPopust());
            skupnaCenaKoncniRacun.setText("Vaša cena zanaša "+this.kosarica.vrniSkupnoCeno()+"€ -10% = "+novaCena+"€");
        }
        else{
            skupnaCenaKoncniRacun.setText("Skupaj: "+this.kosarica.vrniSkupnoCeno()+"€");
        }



    }


    public void zakljuciNakup(ActionEvent actionEvent) throws IOException{
        if(preveriIme() && preveriPriimek() && preveriEmail() && preveriPostnoSt() && preveriNaslov()){
            if(preveriRacun(cena,novaCena,this.kKupiFilmPrekoSpleta.stranka.getSredstva(),this.kosarica.vrniSkupnoCeno(),this.kosarica.getKolicinaFilmov())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nakup");
                alert.setHeaderText("Zaključevanje nakupa");
                alert.setContentText("Čestitamo za uspešen nakup!");
                alert.showAndWait();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fri/ris/blockbuster/viri/prijavnoOkno.fxml")));
                Scene dashboard = new Scene(root);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(dashboard);
                window.show();

            }
            else{
                System.out.println("Prišlo je do neuspšne transakcije");
            }
        }
    }

    private boolean preveriRacun(double cena, double novaCena, double sredstva, double vrniSkupnoCeno, int kolicinaFilmov) {
        if(kolicinaFilmov >= 5 || vrniSkupnoCeno > 50.0){
            if((sredstva - novaCena) >= 0){
                System.out.println("Tranzakcija uspela");
                ZMNakupFilmaPrekoSpleta.posodobiSredstva((sredstva - novaCena));
                System.out.println(this.kKupiFilmPrekoSpleta.stranka.getSredstva()+"€€");
                try{
                    PrintWriter pw = new PrintWriter(new FileWriter(new File(imeRacun.getText().toString()+"_"+priimekRacun.getText().toString()+".txt")));
                    pw.write(imeRacun.getText()+"\n"+priimekRacun.getText()+"\n"+epostaRacun.getText()+"\n"+addressNaslov.getText()+"\n "+postaNslov.getText()+"\n "+
                            prikazRacun.getText()+"\n Stevilo filmov kupljenih: "+stFilmovKoncniRacun.getText()+"\n Skupaj:"+skupnaCenaKoncniRacun.getText()+"\n ");
                    pw.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
                return true;
            }
            else{
                System.out.println("Premalo denarja");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Napaka");
                alert.setHeaderText("Napaka pri transakciji");
                alert.setContentText("Napaka pri transakciji: na računu imate premalo denarja");
                alert.showAndWait();
                return false;
            }
        }
        else{
            if((sredstva - cena) >= 0){
                ZMNakupFilmaPrekoSpleta.posodobiSredstva((sredstva - cena));
                System.out.println(this.kKupiFilmPrekoSpleta.stranka.getSredstva()+"€€€");
                try{
                    PrintWriter pw = new PrintWriter(new FileWriter(new File(imeRacun.getText().toString()+"_"+priimekRacun.getText().toString()+".txt")));
                    pw.write(imeRacun.getText()+"\n"+priimekRacun.getText()+"\n"+epostaRacun.getText()+"\n"+addressNaslov.getText()+"\n"+postaNslov.getText()+"\n"+
                            prikazRacun.getText()+"\n"+stFilmovKoncniRacun.getText()+"\n"+skupnaCenaKoncniRacun.getText()+"\n");
                    pw.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
                return true;
            }
            else{
                System.out.println("Transakcija neuspešna: premalo sredstev na računu");
                System.out.println("Premalo denarja");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Napaka");
                alert.setHeaderText("Napaka pri transakciji");
                alert.setContentText("Napaka pri transakciji: na računu imate premalo denarja");
                alert.showAndWait();
                return false;
            }
        }
    }


   private boolean preveriNaslov() {
        if(addressNaslov.getText() == null || addressNaslov.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setContentText("Napaka pri vnosu Vašega domačega naslova, preverite če ste vnesli Vaš naslov");
            alert.showAndWait();
            return false;
        }

        else{
            return true;
        }
    }


    private boolean preveriPostnoSt() {
        String postna = postaNslov.getText().toString();
        if(postaNslov.getText() == null || postaNslov.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setContentText("Napaka pri vnosu Vaše poštne številke, preverite če ste vnesli Vašo poštno številko");
            alert.showAndWait();
            return false;
        }
        if(postna.matches("[0-9]*?") && postna.length() == 4){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setContentText("Napaka pri vnosu Vaše poštne številke, preverite če Vaša poštna številka vsebuje 4 mestno število");
            alert.showAndWait();
            return true;
        }
        else
        return false;
    }

    private boolean preveriEmail() {
        if(epostaRacun.getText() == null || epostaRacun.getText().trim().isEmpty()){
            //TODO obvesti napako
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setContentText("Napaka pri vnosu Vašega eNaslova, preverite če ste vnesli Vaš eNaslov");
            alert.showAndWait();
            return false;
        }
        else{
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(epostaRacun.getText().toString());
            return m.matches();
        }

    }

    private boolean preveriPriimek() {
        String surname = priimekRacun.getText().toString();
        if(priimekRacun.getText().trim().isEmpty() || priimekRacun.getText() == null){
            //TODO obvesti o napaki
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setContentText("Napaka pri vnosu Vašega priimka, preverite če ste vnesli Vaš priimek");
            alert.showAndWait();
            return false;
        }
        else if(surname.matches(".*[0-9].*")){
            //TODO obvesti o napaki
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setContentText("Napaka pri vnosu Vašega priimka, preverite da Vaš priimek ne vsebuje številk");
            alert.showAndWait();
            return false;
        }
        else{
            return true;
        }
    }

    private boolean preveriIme(){
        String name = imeRacun.getText().toString();
        if(imeRacun.getText().trim().isEmpty() ||imeRacun.getText() == null){
            //TODO: obvesti o napaki
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setContentText("Napaka pri vnosu Vašega imena, preverite če ste vnesli Vaše ime");
            alert.showAndWait();
            return false;
        }
        else if(name.matches(".*[0-9].*")){
            //TODO: obvesti o napaki
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setContentText("Napaka pri vnosu Vašega imena, preverite da Vaše ime ne vsebuje številk");
            alert.showAndWait();
            return false;
        }
        else{
            return true;
        }

    }



    public void nazaj(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fri/ris/blockbuster/viri/ZMNakupFilmaPrekoSpleta.fxml")));
        Scene dashboard = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(dashboard);
        window.show();
    }
}
