package org.openjfx.assignmentFX;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HistoryScene extends ProgramScene {
	
	
private static ObservableList<Transaction> data = FXCollections.observableArrayList();

public static Scene getSceneContent(Stage stage, String accountNumber) {
		

		
		//LAYOUT
	    VBox baseContainer = new VBox();
	    baseContainer.setSpacing(5);
	
	    baseContainer.getStyleClass().add("baseContainer");
	    
	    
	    
	    //HISTORY
		data = readDataFromFile(accountNumber);
		
	
	    TableView<Transaction> table = new TableView<Transaction> ();
	    table.setEditable(true);
	    
	    TableColumn dateCol = new TableColumn("DATE");
	    dateCol.prefWidthProperty().bind(table.widthProperty().divide(3));
	    dateCol.setCellValueFactory(
                new PropertyValueFactory<Transaction, String>("date"));
	    
        TableColumn actionCol = new TableColumn("ACTION");
        actionCol.prefWidthProperty().bind(table.widthProperty().divide(3));
        actionCol.setCellValueFactory(
                new PropertyValueFactory<Transaction, String>("action"));
        
        TableColumn amountCol = new TableColumn("AMOUNT");
        amountCol.prefWidthProperty().bind(table.widthProperty().divide(3));
        amountCol.setCellValueFactory(
                new PropertyValueFactory<Transaction, String>("amount"));
        
        table.setItems(data);
        table.getColumns().addAll(dateCol, actionCol, amountCol);
       
	    
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
        list.addAll(table,returnActionsButton,returLoginButton);
        
        var scene = new Scene(baseContainer, 640, 480);

   
      
        
        return scene;
	}


	
	private static ObservableList<Transaction> readDataFromFile(String accountNumber) {
		
		   
				ObservableList<Transaction> transactions  = FXCollections.observableArrayList();
				
				try {
				
						File transactionsFile = new File("./" + accountNumber +".txt");
						 if(!transactionsFile.exists()){
							 transactionsFile.createNewFile();
						   }
						Scanner reader = new Scanner(transactionsFile);
						
						while(reader.hasNextLine()) {
							
							String currLine = reader.nextLine();
							String[] currLineAsArray = currLine.split("");
							
							
							String date = currLine.substring(4,13);
							String action = "NOT PROVIDED";
							String amount = "";
							
							if(currLine.contains("WITHDRAW")) {
								action = "WITHDRAW";
							}
							else if(currLine.contains("DEPOSIT")){
								action = "DEPOSIT";
							}
							
						
							for(int i=currLineAsArray.length-1;i>=0;i--) {

								if(!currLineAsArray[i].toString().matches("^[0-9.]*$")) {
									break;
								}
								amount= currLineAsArray[i] + amount;
							}
							
							Transaction currTransaction = new Transaction(date,action,amount);
							System.out.println(currTransaction.getAmount().toString());
							transactions.add(currTransaction);
							
				
						}
					 
					     
				   } catch (IOException e) {
					      System.out.println("An error occurred.");
					      e.printStackTrace();
				   }
				
			
				
				return transactions;
			
	
	}
}


