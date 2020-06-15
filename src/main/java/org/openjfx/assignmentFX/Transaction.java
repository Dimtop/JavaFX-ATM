package org.openjfx.assignmentFX;

import javafx.beans.property.SimpleStringProperty;

public class Transaction {
    private  SimpleStringProperty date;
    private  SimpleStringProperty action;
    private  SimpleStringProperty amount;
    
    public Transaction(String date, String action, String amount) {
    		this.date = new SimpleStringProperty(date);
    		this.action = new SimpleStringProperty(action);
    		this.amount = new SimpleStringProperty(amount);
    }

	public  String getDate() {
		return date.get();
	}

	public  void setDate(String fDate) {
		date.set(fDate);
	}
	
	public  String getAction() {
		return action.get();
	}

	public  void setAction(String fAction) {
		action.set(fAction);
	}
	
	public  String getAmount() {
		return amount.get();
	}

	public  void setAmount(String fAmount) {
		amount.set(fAmount);
	}
    
    
}