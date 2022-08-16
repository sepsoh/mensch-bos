module com.sepsoh.menschbos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.sepsoh.menschbos to javafx.fxml;
    exports com.sepsoh.menschbos;
    exports com.sepsoh.menschbos.playwithbots;
    opens com.sepsoh.menschbos.playwithbots to javafx.fxml;
    exports com.sepsoh.menschbos.mensch;
    opens com.sepsoh.menschbos.mensch to javafx.fxml;
}