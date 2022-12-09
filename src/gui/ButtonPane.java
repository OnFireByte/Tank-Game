package gui;


import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sharedObject.RenderableHolder;

public class ButtonPane extends VBox {
	
	
	public ButtonBase btn1;
	public ButtonBase btn2;
	public ButtonBase btn3;
	
	public ButtonPane() {
		super();
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(30);
		this.setPadding(new Insets(200));
		
		//Name
		Text txt = new Text("Tank Game");
		txt.setFont(RenderableHolder.buttonFont);
		txt.setTabSize(50);
		
		//play button
		startButton();
	
		//help button
		helpButton();
		
		//exit button
		exitButton();
	
		this.getChildren().addAll(txt ,btn1,btn2,btn3 );
	}
	public void startButton() {
		btn1 = new ButtonBase("Play");
		btn1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				//play audio click
				RenderableHolder.clickSound.play();
				
				//change to playScene
				
			}
		});
	}
	public void helpButton() {
		btn2 = new ButtonBase("Help");
	}
	
	
	public void exitButton() {
		btn3 = new ButtonBase("Exit");
		btn3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				//play audio click
				RenderableHolder.clickSound.play();
				//exit
				System.exit(0);
			}
		});
	}
	
	

}
