package fri.ris.blockbuster.blockbusterinc;

import fri.ris.blockbuster.blockbusterinc.Entitete.Stranka;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class RegistracijskoOknoController {
    public PasswordField potrdiGesloReg;
    public PasswordField gesloReg;
    public TextField usernameReg;
    public static Stranka stranka;
    public TextField emailReg;
    public TextField priimekReg;
    public TextField imeReg;

    public void preveriPodatkeInOdpriGlavnoOkno(ActionEvent actionEvent) throws IOException{
        //ZAPIŠI ŠE PODATKE V DATOTEKO
        if(preveriIme() && preveriPriimek() && preveriMail() && preveriUsername() && preveriGeslo()){
            stranka = new Stranka(imeReg.getText().toString(),priimekReg.getText().toString(),usernameReg.getText().toString(),emailReg.getText().toString());
            zapisiVDatoteko();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registracija");
            alert.setHeaderText("Ustvarjanje računa");
            alert.setContentText("Registracija je bila uspešna");
            alert.showAndWait();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fri/ris/blockbuster/viri/prijavnoOkno.fxml")));
            Scene dashboard = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(dashboard);
            window.show();
        }{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setContentText("Napaka pri vnosu Vaših podatkov, preverite če ste vnesli vse Vaše podatke");
            alert.showAndWait();
        }


    }

    private void zapisiVDatoteko() {
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(("uporabniki.txt"),true));
            String niz = imeReg.getText().trim().toString() + " " + priimekReg.getText().trim().toString() + " " + usernameReg.getText().trim().toString() + " " + gesloReg.getText().trim().toString() + " " + emailReg.getText().trim().toString();
            pw.append(niz).append("\n");
            pw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private boolean preveriGeslo() {
        String passwd = gesloReg.getText().toString();
        String confirmPasswd = potrdiGesloReg.getText().toString();
        if(gesloReg.getText() == null || potrdiGesloReg.getText() == null || gesloReg.getText().trim().isEmpty() || potrdiGesloReg.getText().trim().isEmpty()){
            //TODO obvesti napako
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setContentText("Napaka pri vnosu gesel, preverite če ste vnesli enaki gesli");
            alert.showAndWait();
            return false;
        }
        return passwd.equals(confirmPasswd);
    }

    private boolean preveriUsername() {
        boolean seNiZapisan = true;
        File f = new File("uporabniki.txt");
        try{
            if(usernameReg.getText() == null || usernameReg.getText().trim().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Napaka");
                alert.setContentText("Napaka pri vnosu uporabniškega Imena, preverite če ste vnesli uporabniško ime");
                alert.showAndWait();
                return false;
                //TODO obvesti napako
            }
            else{
                //BufferedReader br = new BufferedReader(new FileReader(new File("uporabniki.txt")));
                Scanner sc = new Scanner(new File("uporabniki.txt"));
                String vrstica = "";
                while(sc.hasNextLine()){
                    vrstica = sc.nextLine();
                    String [] zac = vrstica.split(" ");
                    System.out.println(vrstica);
                    System.out.println(zac.length);
                    if(zac[2].equals(usernameReg.getText().trim().toString())){
                        seNiZapisan = false;
                        sc.close();
                        return seNiZapisan;

                    }
                }
                return true;
            }
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean preveriMail() {
        if(emailReg.getText() == null || emailReg.getText().trim().isEmpty()){
            //TODO obvesti napako
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setContentText("Napaka pri vnosu epošte, preverite če ste vnesli Vaš poštni naslov");
            alert.showAndWait();
            return false;
        }
        else{
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(emailReg.getText().toString());
            return m.matches();
        }

    }

    private boolean preveriPriimek() {
        String surname = priimekReg.getText().toString();
        if(priimekReg.getText().trim().isEmpty() || priimekReg.getText() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setContentText("Napaka pri vnosu Vašega priimka, preverite če ste vnesli Vaš priimek");
            alert.showAndWait();
            return false;
        }
        else if(surname.matches(".*[0-9].*")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setContentText("Napaka pri vnosu Vašega priimka, preverite da niste vnesli številk v Vaš priimek");
            alert.showAndWait();
            return false;
        }
        else{
            return true;
        }
    }

    private boolean preveriIme(){
        String name = imeReg.getText().toString();
        if(imeReg.getText().trim().isEmpty() ||imeReg.getText() == null){
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
            alert.setContentText("Napaka pri vnosu Vašega imena, preverite da niste vnesli številk v Vaše ime");
            alert.showAndWait();
            return false;
        }
        else{
            return true;
        }

    }
}
