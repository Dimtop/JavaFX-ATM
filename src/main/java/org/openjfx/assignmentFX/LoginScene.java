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

public class LoginScene extends ProgramScene {

	
	private static ArrayList<ArrayList<String>> accounts  = new ArrayList<ArrayList<String>>();
	public LoginScene() {
		
	}

	public static Scene getSceneContent(Stage stage) {
		
		//ACCOUNTS
		ArrayList<String> account1 = new ArrayList<String>();
		ArrayList<String> account2 = new ArrayList<String>();
		ArrayList<String> account3 = new ArrayList<String>();
		
		account1.add("11110000");
		account1.add("1234");

		account2.add("11110001");
		account2.add("5678");
		
		account3.add("11110002");
		account3.add("2345");
		
		accounts.add(account1);
		accounts.add(account2);
		accounts.add(account3);
		//LAYOUT
        VBox baseContainer = new VBox();
        baseContainer.setSpacing(5);
  
        baseContainer.getStyleClass().add("baseContainer");
       
        //PROMT
        Label loginPrompt = new Label("LOG IN");
        baseContainer.setMargin(loginPrompt,new Insets(20,20,20,20));
        loginPrompt.getStyleClass().add("loginPrompt");
        
        //LABEL
        Label accountNumberLabel = new Label("Account number");
        baseContainer.setMargin(loginPrompt,new Insets(20,20,20,20));
        accountNumberLabel.getStyleClass().add("accountNumberLabel");
        
        //INPUT
        TextField accountNumberInput = new TextField();
        baseContainer.setMargin(accountNumberInput,new Insets(20,20,20,20));
        accountNumberInput.getStyleClass().add("accountNumberInput");
        accountNumberInput.setMaxWidth(200);
        accountNumberInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

            }
        });
        
        //LABEL
        Label passwordLabel = new Label("Password");
        baseContainer.setMargin(passwordLabel,new Insets(20,20,20,20));
        passwordLabel.getStyleClass().add("passwordLabel");
        
        //INPUT
        TextField passwordInput = new TextField();
        baseContainer.setMargin(passwordInput,new Insets(20,20,20,20));
        passwordInput.getStyleClass().add("accountNumberInput");
        passwordInput.setMaxWidth(200);
        passwordInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

            }
        });
        
        //ERROR
        Label error = new Label("");
        baseContainer.setMargin(error,new Insets(20,20,20,20));
        error.getStyleClass().add("error");
        
        //SUBMIT
        Button submitButton = new Button("Submit");
        submitButton.getStyleClass().add("submitButton");
        baseContainer.setMargin(submitButton,new Insets(20,20,20,20));
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            	boolean accountFound = false;
            	for(ArrayList<String> account : accounts) {
            		if(account.get(0).equals(accountNumberInput.getText())) {
            			accountFound = true;
            			if(account.get(1).equals(passwordInput.getText())){
            				error.setText("");
            				Scene actionsScene = ActionsScene.getSceneContent(stage, accountNumberInput.getText());
            				actionsScene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
            		        stage.setScene(actionsScene);
            				break;
            			}
            			else {
            				error.setText("Wrong password.");
            			}
            		}
            	}
            	
            	if(!accountFound) {
            		error.setText("Account not found.");
            	}
        	
            }
        });
        
   
        
        
        
      //retrieving the observable list of the VBox 
        ObservableList list = baseContainer.getChildren(); 
        
        //Adding all the nodes to the observable list 
        list.addAll(loginPrompt,accountNumberLabel,accountNumberInput,passwordLabel,passwordInput,submitButton,error);
        
        var scene = new Scene(baseContainer, 640, 480);

   
      
        
        return scene;
	}

}
