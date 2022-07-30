package fri.ris.blockbuster.blockbusterinc;
import fri.ris.blockbuster.blockbusterinc.Entitete.Film;
import fri.ris.blockbuster.blockbusterinc.Entitete.Stranka;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ZMNakupFilmaPrekoSpleta implements Initializable {
    public static KKupiFilmPrekoSpleta kKupiFilmPrekoSpleta;
    public TableView<Film> tabelaFilmov;
    public Label stFilmovVKosarici;
    public Label kosaricaSkupnaCena;
    private static Stranka stranka;
    public Label uporabnik;
    public ImageView slikaUporabnika;
    public Label imeOsebniRacun;
    public Label priimekOsebniRacun;
    public Label usernameOsebniRacun;
    public Label epostaOsebniRacun;
    public TextField preberiSredstva;
    public Label balanceOsebniRacun;
    @FXML
    private TableColumn<Film,Integer> id;
    @FXML
    private TableColumn<Film,String> imeFilma;
    @FXML
    private TableColumn<Film,String> opisFilma;
    @FXML
    private TableColumn<Film,String> zanr;
    @FXML
    private TableColumn<Film,Double> cenaFilma;
    @FXML
    private TableColumn<Film,Double> ocenaFilma;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Film> filmi = new ArrayList<Film>();
        filmi.add(new Film(1,"Red Notice","V filmu igra Ryan Reynolds","akcijski",29.3,6.8));
        filmi.add(new Film(2,"The Blacklist","Ni film, ampak serija","akcijski",12.5,9.8));
        filmi.add(new Film(3,"The Da Vinci Code","Best movie ever","triler,pustolovski,akcijski",30,10));
        filmi.add(new Film(4,"The Dukes of Hazzard","Star film","pustolovski,komedija",16.7,5.0));
        filmi.add(new Film(5,"The King of Queens","Se ena serija","komedija",10,5.5));
        filmi.add(new Film(6,"The Fast and The Furious","Vin Bencin","akcijski, triler",12.9,7.9));
        filmi.add(new Film(7,"Burn Notice","Vohunska serija","akcijski",8.9,8.9));
        filmi.add(new Film(8,"Grown ups","Adam Sandler in \"the gang\"","komedija",18.1,6.0));
        filmi.add(new Film(9,"The Murder on the Orient Express","Zanimiv film","mistiriozni, triler",11.2,7.2));
        filmi.add(new Film(10,"Troy","Kinda good movie","akcijski, zgodovinski",7.6,8.3));

        slikaUporabnika.setImage(new Image("./slike/uporabnik.gif"));
        this.kKupiFilmPrekoSpleta = new KKupiFilmPrekoSpleta(PrijavnoOknoController.stranka);
        this.stranka = this.kKupiFilmPrekoSpleta.vrniStranko();
        //System.out.println(this.kKupiFilmPrekoSpleta.vrniOsebniPodatke());
        balanceOsebniRacun.setText("Trenutno stanje na računu: "+this.stranka.getSredstva()+"€");
        imeOsebniRacun.setText(imeOsebniRacun.getText()+" "+this.stranka.getIme());
        priimekOsebniRacun.setText(priimekOsebniRacun.getText()+" "+this.stranka.getPriimek());
        epostaOsebniRacun.setText(epostaOsebniRacun.getText()+" "+this.stranka.getePosta());
        usernameOsebniRacun.setText(usernameOsebniRacun.getText()+" "+this.stranka.getUsername());
        this.kKupiFilmPrekoSpleta = new KKupiFilmPrekoSpleta(filmi,tabelaFilmov);

        System.out.println("SEM TUKAJ PRVIC");
        pricniNakup();
    }

    public void pricniNakup(){
        System.out.println("SEM TUKAJ");
        prikaziSeznamFilmov();
    }

    private void prikaziSeznamFilmov() {
        System.out.println("SEM V PRIKAZI FILMOV");
        ObservableList<Film> items = FXCollections.observableArrayList();
        ArrayList<Film> films = this.kKupiFilmPrekoSpleta.vrniFilme();
        for(Film film : films){
            items.add(film);
            System.out.println(film.getCena());
        }
        id.setCellValueFactory(new PropertyValueFactory<Film,Integer>("id"));
        imeFilma.setCellValueFactory(new PropertyValueFactory<Film,String>("imeFilma"));
        opisFilma.setCellValueFactory(new PropertyValueFactory<Film,String>("opisFilma"));
        zanr.setCellValueFactory(new PropertyValueFactory<Film,String>("genre"));
        cenaFilma.setCellValueFactory(new PropertyValueFactory<Film,Double>("cena"));
        ocenaFilma.setCellValueFactory(new PropertyValueFactory<Film,Double>("ocenaFilma"));
        tabelaFilmov.setItems(items);

    }

    public void prikaziPodrobnostiFilma(ActionEvent actionEvent) {
        Film f = this.kKupiFilmPrekoSpleta.vrniPodrobnostiFilma();//tabelaFilmov.getSelectionModel().getSelectedItem();
        if(f != null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Podrobnosti Filma");
            alert.setHeaderText("Podrobnosti");
            alert.setContentText("Film: \n"+"Ime: "+f.getImeFilma()+", Opis: "+f.getOpisFilma()+", Žanr: "+f.getGenre()+" Cena filma: "+f.getCena() +"€"+" , ocena "+f.getOcenaFilma()+"/10");

            alert.showAndWait();
        }
        else{
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Izberite Film");
            a.setHeaderText("Opozorilo");
            a.setContentText("Prosimo prej izberite film");
            a.showAndWait();
        }

    }

    public void dodajFilmVKosarico(ActionEvent actionEvent) {
        Film f = this.kKupiFilmPrekoSpleta.vrniPodrobnostiFilma();
        if(f != null){
            this.kKupiFilmPrekoSpleta.dodajFilmVKosarico(f);
            stFilmovVKosarici.setText("Število filmov v košarici: "+this.kKupiFilmPrekoSpleta.velikostKosarice());
            kosaricaSkupnaCena.setText("Skupna cena: "+this.kKupiFilmPrekoSpleta.vrniSkupnoCeno()+"€");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dodaj Film");
            alert.setHeaderText("Opozorilo");
            alert.setContentText("Prosimo prej izberite film");
            alert.showAndWait();
        }
    }

    public void odstraniIzKosa(ActionEvent actionEvent) {
        Film f = this.kKupiFilmPrekoSpleta.vrniPodrobnostiFilma();
        if(f != null){
            this.kKupiFilmPrekoSpleta.odstraniIzKosarice(f);
            stFilmovVKosarici.setText("Število filmov v košarici: "+this.kKupiFilmPrekoSpleta.velikostKosarice());
            kosaricaSkupnaCena.setText("Skupna cena: "+this.kKupiFilmPrekoSpleta.vrniSkupnoCeno()+"€");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Odstrani Film");
            alert.setHeaderText("Opozorilo");
            alert.setContentText("Prosimo prej izberite film");
            alert.showAndWait();
        }


    }
    public void koncajNakup(ActionEvent actionEvent) throws IOException {
       this.kKupiFilmPrekoSpleta.koncajNakup(actionEvent);
    }

    public void dodajSredstvaNaOsebniRacun(ActionEvent actionEvent) {
        if(preberiSredstva.getText() != null && preberiSredstva.getText().toString().isEmpty()){
            //TODO: DODAJ ALERT O NAPAKI DA NE MOREŠ PRAZNO DODATI
        }
        else if(preberiSredstva.getText().matches("^[0-9]+$")){
            String stevilo = preberiSredstva.getText().toString();
            System.out.println(stevilo+"€");
            double pretvori = Double.parseDouble(stevilo);
            if(pretvori > 0){
                stranka.setSredstva(pretvori);
                balanceOsebniRacun.setText("Trenutno stanje na računu: "+ stranka.getSredstva()+"€");
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Napaka");
                alert.setHeaderText("Napaka pri dodajanju sredstev");
                alert.setContentText("Prosimo, da ne dodajate negativnih vrednosti");
                alert.showAndWait();
            }

        }
    }

    public static void posodobiSredstva(double vrednost){
        stranka.setSredstva(vrednost);
        //balanceOsebniRacun.setText("Trenutno stanje na računu: "+ stranka.getSredstva()+"€");
    }

    public void izhodIzAplikacije(ActionEvent actionEvent) {
        System.exit(0);
    }
}