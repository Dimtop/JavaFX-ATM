package org.openjfx.assignmentFX;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DepositScene extends ProgramScene {
public static Scene getSceneContent(Stage stage, String accountNumber) {
		
		
		//LAYOUT
	    VBox baseContainer = new VBox();
	    baseContainer.setSpacing(5);
	
	    baseContainer.getStyleClass().add("baseContainer");
	    
	    
        //PROMT
        Label amountPrompt = new Label("Please enter an amount:");
        baseContainer.setMargin(amountPrompt,new Insets(20,20,20,20));
        amountPrompt.getStyleClass().add("amountPrompt");
        
       
        
        //AMOUNT
        TextField amount = new TextField();
        baseContainer.setMargin(amount,new Insets(20,20,20,20));
        amount.getStyleClass().add("amount");
        amount.setMaxWidth(200);
        amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
            	
            	 if(!newValue.matches("^[0-9.]*$")) {
            		 amount.setText(oldValue);
                 }
            }
        });
        
        //RECEIPT
        CheckBox receipt = new CheckBox("I want a receipt.");
        receipt.getStyleClass().add("receipt");
        baseContainer.setMargin(receipt,new Insets(20,20,20,20));

        //ERROR
        Label error = new Label("");
        baseContainer.setMargin(error,new Insets(20,20,20,20));
        error.getStyleClass().add("error");
        
        
      //SUBMIT
        Button submitButton = new Button("Submit");
        submitButton.getStyleClass().add("submitButton");
        baseContainer.setMargin(submitButton,new Insets(20,20,20,20));
        submitButton.setMaxWidth(300);
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            	
            	if(amount.getText().equals("")) {
            		error.setText("Please enter an amount.");
            	}
            	else {
            		error.setText("");
            		writeToAccountFileFile(accountNumber,amount.getText().toString());
                	
                	if(receipt.isSelected()) {
                		createReceiptFile(accountNumber,amount.getText().toString());
               
                	}

             		Scene actionsScene = ActionsScene.getSceneContent(stage,accountNumber);
                	actionsScene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
    		        stage.setScene(actionsScene);
            	}
            	
        	
            }
        });
        
      //RETURN ACTIONS
        Button returnActionsButton = new Button("Return to actions");
        returnActionsButton.getStyleClass().add("returnActionsButton");
        baseContainer.setMargin(returnActionsButton,new Insets(20,20,20,20));
        returnActionsButton.setMaxWidth(200);
        returnActionsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Scene actionsScene = ActionsScene.getSceneContent(stage,accountNumber);
            	actionsScene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
		        stage.setScene(actionsScene);
            }
        });
        
        //RETURN LOGIN
        Button returLoginButton = new Button("Return to login");
        returLoginButton.getStyleClass().add("returLoginButton");
        baseContainer.setMargin(returLoginButton,new Insets(20,20,20,20));
        returLoginButton.setMaxWidth(200);
        returLoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Scene loginScene = LoginScene.getSceneContent(stage);
            	loginScene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
		        stage.setScene(loginScene);
            }
        });
        
   
        
        
        
      //retrieving the observable list of the VBox 
        ObservableList list = baseContainer.getChildren(); 
        
        //Adding all the nodes to the observable list 
        list.addAll(amountPrompt,amount,receipt,submitButton,error,returnActionsButton,returLoginButton);
        
        var scene = new Scene(baseContainer, 640, 480);

   
      
        
        return scene;
	}


	private static void createReceiptFile(String accountNumber, String amount) {
		   
		SimpleDateFormat formatter= new SimpleDateFormat("HH-mm-ss");
		SimpleDateFormat formatterDate= new SimpleDateFormat("dd-M-yyyy");
		Date date = new Date(System.currentTimeMillis());
		String timeStr = formatter.format(date).toString();
		String dateStr = formatterDate.format(date).toString();
		
		String protectedAccountNumber = accountNumber.substring(0, accountNumber.length()-4).concat("****");
		
			try {
			   	System.out.println("receipt"+timeStr+".txt");
				   File f = new File("./receipt"+timeStr+".txt");
				   if(!f.exists()){
				     f.createNewFile();
				   }else{
				     System.out.println("File already exists");
				   }
			      FileWriter writer = new FileWriter("./receipt"+timeStr+".txt");
			      writer.write("*********************"  +'\n');
			      writer.write("Account number: " + protectedAccountNumber +'\n');
			      writer.write("Action: " + "DEPOSIT"+'\n');
			      writer.write("Amount: " + amount + " EUR" + '\n');
			      writer.write("Date: " + dateStr+ " L" +'\n');
			      writer.write("*********************"  +'\n');
			      writer.close();
		   } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		   }
	}
	
	private static void writeToAccountFileFile(String accountNumber, String amount) {
		   
		SimpleDateFormat formatter= new SimpleDateFormat("HH-mm-ss");
		SimpleDateFormat formatterDate= new SimpleDateFormat("dd-M-yyyy");
		Date date = new Date(System.currentTimeMillis());
		String timeStr = formatter.format(date).toString();
		String dateStr = formatterDate.format(date).toString();
		
		String protectedAccountNumber = accountNumber.substring(0, accountNumber.length()-5).concat("****");
		
			try {
		
				   File f = new File("./" + accountNumber +".txt");
				   if(!f.exists()){
				     f.createNewFile();
				   }
			      FileWriter writer = new FileWriter("./" + accountNumber +".txt",true);
			      writer.write("--		"+ dateStr + "		DEPOSIT" + "		" + amount+'\n');
			      writer.close();
		   } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		   }
	}
}
