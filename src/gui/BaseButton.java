package gui;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class BaseButton extends Button{
	
	public BaseButton(String name) {
		
		super(name);
		this.setPrefHeight(50);
		this.setPrefWidth(300);
		this.setStyle("-fx-background-color: darkblue; "
				+ "-fx-border-color: blue; "
				+ "-fx-font-family: \"Unispace\"; "
				+ "-fx-text-fill: white; "
				+ "-fx-border-width: 5px;" 
				+ "-fx-font-weight: bold; "
				+ "-fx-font-size:20 ;");
		
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				setPrefHeight(55);
				setPrefWidth(320);
				setStyle("-fx-background-color: blue; "
						+ "-fx-border-color: black; "
						+ "-fx-font-family: \"Unispace\"; "
						+ "-fx-text-fill: white; "
						+ "-fx-border-width: 5px;" 
						+ "-fx-font-weight: bold; "
						+ "-fx-font-size:20 ;");
			}
		});
		this.setOnMouseExited(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				setPrefHeight(50);
				setPrefWidth(300);
				setStyle("-fx-background-color: darkblue; "
						+ "-fx-border-color: blue; "
						+ "-fx-text-fill: white; "
						+ "-fx-font-family: \"Unispace\"; "
						+ "-fx-border-width: 5px;" 
						+ "-fx-font-weight: bold; "
						+ "-fx-font-size:20 ;");
			}
		});
	}

}
