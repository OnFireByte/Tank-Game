package gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;


public class BaseButton extends Button{
	
	public BaseButton(String name , int w , int h) {
		
		super(name);
		this.setFont(RenderableHolder.getFont(15));
		this.setTextFill(Color.AZURE);
		ImageView b = RenderableHolder.getButton(w, h);
		ImageView bp = RenderableHolder.getButtonPressed(w-3, h-3);
		this.setBackground(null);
		this.setPadding(Insets.EMPTY);
		this.setContentDisplay(ContentDisplay.CENTER);
		this.setGraphic(b);
		
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				setGraphic(bp);
			}
		});
		this.setOnMouseExited(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				setGraphic(b);
			}
		});
	}

}
