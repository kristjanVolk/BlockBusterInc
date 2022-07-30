package fri.ris.blockbuster.blockbusterinc;
import fri.ris.blockbuster.blockbusterinc.Entitete.Film;
import fri.ris.blockbuster.blockbusterinc.Entitete.Kosarica;
import fri.ris.blockbuster.blockbusterinc.Entitete.Stranka;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

public class KKupiFilmPrekoSpleta {
    private ArrayList<Film> filmi;
    private TableView<Film> tabelaFilmov;
    private Label balanca;
    public static HashMap<Film,Integer> kos;
    public static Stranka stranka;
    public static Kosarica kosarica;
    public KKupiFilmPrekoSpleta(Stranka stranka){
        this.stranka = stranka;
    }
    public KKupiFilmPrekoSpleta(){}

    public KKupiFilmPrekoSpleta(ArrayList<Film> filmi, TableView<Film> tabelaFilmov){
        this.filmi = filmi;
        this.tabelaFilmov = tabelaFilmov;
        this.kos = new HashMap<>();
        this.kosarica = new Kosarica(this.kos);
    }
    public int velikostKosarice(){
       return this.kosarica.getKolicinaFilmov();
    }

    public ArrayList<Film> vrniFilme(){
        ArrayList<Film> temp = new ArrayList<>();
        for (int i = 0; i < filmi.size(); i++) {
            temp.add(filmi.get(i));
        }
        return temp;
    }

    public Film vrniPodrobnostiFilma(){
        return tabelaFilmov.getSelectionModel().getSelectedItem();
    }

    public void dodajFilmVKosarico(Film f) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Dodajanje v Košarico");
        confirm.setHeaderText("Potrditev");
        confirm.setContentText("Ali želite dodati film: "+f.getImeFilma()+" v košarico");
        Optional<ButtonType> result = confirm.showAndWait();
        if(!result.isPresent())
            return;
        else if(result.get() == ButtonType.OK){
            if(kos.containsKey(f)){
                kos.put(f,kos.get(f) + 1);
            }
            else{
                kos.put(f,1);
            }
        }
        else if(result.get() == ButtonType.CANCEL)
            return;

    }

    public void odstraniIzKosarice(Film f){
        if(!kos.containsKey(f)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setHeaderText("Napaka pri odstranjevanju filma iz košarice");
            alert.setContentText("Film, ki ste ga hoteli odstraniti iz košarice, ni v košarici");
            alert.showAndWait();
        }
        else{
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Odstranjevanje iz Košarice");
            confirm.setHeaderText("Potrditev");
            confirm.setContentText("Ali želite odstraniti film: "+f.getImeFilma()+" iz košarice");
            Optional<ButtonType> result = confirm.showAndWait();
            if(!result.isPresent()) {
                return;
            }
            else if (result.get() == ButtonType.OK){
                int stFilmov = this.kos.get(f);
                if(stFilmov > 1){
                    this.kos.put(f,this.kos.get(f) - 1);
                }
                else{
                    this.kos.remove(f);
                }
            }
            else if(result.get() == ButtonType.CANCEL)
                return;
        }
    }

    public String vrniOsebniPodatke(){
        return this.stranka.vrniOsebnePodatke();
        //return this.stranka.vrniOsebnePodatke();
    }
    public Stranka vrniStranko(){
        return this.stranka;
    }

    public double vrniSkupnoCeno() {
       return this.kosarica.vrniSkupnoCeno();
    }


    public void koncajNakup(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Zakljucevanje nakupa");
        alert.setHeaderText("Opozorilo");
        alert.setContentText("Ali hočete zaključiti nakup");
        Optional<ButtonType> result = alert.showAndWait();
        if(!result.isPresent()) {
            return;
        }
        else if (result.get() == ButtonType.OK){
            this.kosarica = new Kosarica(this.kos);
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fri/ris/blockbuster/viri/KoncniRacun.fxml")));
            Scene dashboard = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(dashboard);
            window.show();
        }
        else if(result.get() == ButtonType.CANCEL)
            return;
    }
}
