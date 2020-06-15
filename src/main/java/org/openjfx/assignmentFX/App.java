package org.openjfx.assignmentFX;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
    	
    	Scene cardScene = LoginScene.getSceneContent(stage);
    	cardScene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        stage.setScene(cardScene);
        stage.setTitle("ATM");
        stage.show();
    
    }

    public static void main(String[] args) {
        launch();
    }
	
    
}