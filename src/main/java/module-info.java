module fri.ris.blockbuster.blockbusterinc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;


    //opens fri.ris.blockbuster.blockbusterinc to javafx.fxml;
    //opens fri.ris.blockbuster.blockbusterinc.Entitete to javafx.fxml;
    opens fri.ris.blockbuster.blockbusterinc;
    exports fri.ris.blockbuster.blockbusterinc;
    exports fri.ris.blockbuster.blockbusterinc.Entitete;
}