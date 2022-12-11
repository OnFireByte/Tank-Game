package gui.mainMenu;

import gui.BaseButton;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import sharedObject.RenderableHolder;

public class HelpPane extends StackPane{
	
	private boolean isShowed;
	

	public HelpPane() {
		super(new ImageView(RenderableHolder.test));
		this.setMaxSize(700, 500);
		this.setTranslateX(1000);
		this.isShowed = false;
		
		HBox closeButtonPane = new HBox();
		closeButtonPane.setPadding(new Insets(30, 120, 0, 700));
	
		//---------------------------close button-----------------------------------------------------------------------------------------
		BaseButton btn = new BaseButton("X" , 40 , 40);
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				RenderableHolder.clickSound.play();
				//close HelpPane
				showHelpPane();
			}
		});
		//--------------------------------------------------------------------------------------------------------------------------------
		
		closeButtonPane.getChildren().add(btn);
		this.getChildren().add(closeButtonPane);
		
	}
	
	
	public void showHelpPane() {
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(this);
		if (isShowed) {
			transition.setToX(1000);
			isShowed = false;
		} else {
			transition.setToY(0);
			transition.setToX(0);
			isShowed = true;
		}
		transition.play();
	}
	
	

}
