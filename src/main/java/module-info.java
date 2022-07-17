module com.sepsoh.menschbos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.sepsoh.menschbos to javafx.fxml;
    exports com.sepsoh.menschbos;
}