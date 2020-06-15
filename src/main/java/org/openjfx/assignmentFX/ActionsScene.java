package org.openjfx.assignmentFX;

import java.util.ArrayList;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ActionsScene  extends ProgramScene {
	
	
	
public static Scene getSceneContent(Stage stage, String accountNumber) {
		
		
		//LAYOUT
	    VBox baseContainer = new VBox();
	    baseContainer.setSpacing(5);
	
	    baseContainer.getStyleClass().add("baseContainer");
	    
	    
        //PROMT
        Label actionPrompt = new Label("Please choose an action:");
        baseContainer.setMargin(actionPrompt,new Insets(20,20,20,20));
        actionPrompt.getStyleClass().add("actionPrompt");
        
       
        
        //WITHDRAW
        Button withdrawButton = new Button("Withdraw");
        withdrawButton.getStyleClass().add("withdrawButton");
        baseContainer.setMargin(withdrawButton,new Insets(20,20,20,20));
        withdrawButton.setMaxWidth(200);
        withdrawButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            	Scene withdrawScene = WithdrawScene.getSceneContent(stage,accountNumber);
            	withdrawScene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
		        stage.setScene(withdrawScene);
        	
            }
        });
        
      //DEPOSIT
        Button depositButton = new Button("Deposit");
        depositButton.getStyleClass().add("depositButton");
        baseContainer.setMargin(depositButton,new Insets(20,20,20,20));
        depositButton.setMaxWidth(200);
        depositButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            	Scene depositScene = DepositScene.getSceneContent(stage,accountNumber);
            	depositScene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
		        stage.setScene(depositScene);
        	
            }
        });
        
      //HISTORY
        Button historyButton = new Button("Account history");
        historyButton.getStyleClass().add("historyButton");
        baseContainer.setMargin(historyButton,new Insets(20,20,20,20));
        historyButton.setMaxWidth(200);
        historyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            	
            	Scene historyScene = HistoryScene.getSceneContent(stage,accountNumber);
            	historyScene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
		        stage.setScene(historyScene);
            }
        });
        
   
        
        
        
      //retrieving the observable list of the VBox 
        ObservableList list = baseContainer.getChildren(); 
        
        //Adding all the nodes to the observable list 
        list.addAll(actionPrompt,withdrawButton,depositButton,historyButton);
        
        var scene = new Scene(baseContainer, 640, 480);

   
      
        
        return scene;
	}
}
