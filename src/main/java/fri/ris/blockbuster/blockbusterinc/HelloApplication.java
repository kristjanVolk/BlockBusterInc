package fri.ris.blockbuster.blockbusterinc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Parent parent = FXMLLoader.load(getClass().getResource("/fri/ris/blockbuster/viri/hello-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fri/ris/blockbuster/viri/hello-view.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("BlockBusterInc!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}