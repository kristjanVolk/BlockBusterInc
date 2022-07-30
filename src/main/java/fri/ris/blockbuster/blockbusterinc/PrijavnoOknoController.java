package fri.ris.blockbuster.blockbusterinc;

import fri.ris.blockbuster.blockbusterinc.Entitete.Stranka;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class PrijavnoOknoController {
    @FXML
    public PasswordField prijavnoGeslo;
    @FXML
    public TextField prijavnoUsername;
    public static Stranka stranka;
    public static boolean preveri = false;
    public void preveriUporabnika(ActionEvent actionEvent) {
        try{
            Scanner sc = new Scanner(new File("uporabniki.txt"));
            ArrayList<String> uporabniki = new ArrayList<>();
            while(sc.hasNextLine()){
                uporabniki.add(sc.nextLine().trim());

            }
            String [] s = preveriUpor(uporabniki);
            if(preveri){
                stranka = new Stranka(s[0],s[1],s[2],s[3]);
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fri/ris/blockbuster/viri/ZMNakupFilmaPrekoSpleta.fxml")));
                Scene dashboard = new Scene(root);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(dashboard);
                window.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Napaka");
                alert.setHeaderText("Napaka pri prijavi");
                alert.setContentText("Prišlo je do napake pri prijavi preverite če ste vnesli pravilne podatke");
                alert.showAndWait();
            }
            sc.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private String[] preveriUpor(ArrayList<String> uporabniki) {
        String[] stranka = new String[4];
        for(String user : uporabniki){
            String [] temp = user.split(" ");
            if(temp[2].equals(prijavnoUsername.getText().toString().trim()) && temp[3].equals(prijavnoGeslo.getText().trim().toString())) {
                stranka[0] = temp[0];
                stranka[1] = temp[1];
                stranka[2] = temp[2];
                stranka[3] = temp[4];
                preveri = true;
                return stranka;
            }
        }
        preveri = false;
        return stranka;
    }

}
