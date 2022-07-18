package com.sepsoh.menschbos;

import com.sepsoh.menschbos.data.AppData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import java.io.IOException;

public class Main extends Application {

    static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle(AppData.appTitle);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }
    public static Scene changeScene(String fxmlPath , String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlPath));
            Scene scene = new Scene(fxmlLoader.load());
            Main.stage.setTitle(title);
            Main.stage.setScene(scene);
            Main.stage.show();
            return scene;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;

    }
    public static Scene changeScene(String fxmlPath) {
        return changeScene(fxmlPath,AppData.appTitle);

    }

}