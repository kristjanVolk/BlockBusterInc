package fri.ris.blockbuster.blockbusterinc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class HelloController {
    @FXML
    private Label welcomeText;
    private AnchorPane content;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void prijavnoOkno(ActionEvent actionEvent1) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fri/ris/blockbuster/viri/prijavnoOkno.fxml")));
        Scene dashboard = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent1.getSource()).getScene().getWindow();
        window.setScene(dashboard);
        window.show();
    }

    public void registracijskoOkno(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fri/ris/blockbuster/viri/RegistracijskoOkno.fxml")));
        Scene dashboard = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(dashboard);
        window.show();
    }
}