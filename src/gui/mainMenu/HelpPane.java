package gui.mainMenu;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
		Button btn = new Button("X");
		btn.setPrefSize(50,50);
		btn.setStyle("-fx-background-color: transparent; "
				+ "-fx-border-color: blue;"
				+ "-fx-font-family: \"Unispace\"; "
				+ " -fx-border-radius: 30;"
				+ "-fx-text-fill: blue; "
				+ "-fx-border-width: 6px;"
				+ "-fx-font-weight: bold;"
				+ "-fx-font-size:20;");
		btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				btn.setPrefSize(53,53);
				btn.setStyle("-fx-background-color: transparent; "
						+ "-fx-border-color: darkblue;"
						+ "-fx-font-family: \"Unispace\"; "
						+ " -fx-border-radius: 30;"
						+ "-fx-text-fill: darkblue; "
						+ "-fx-border-width: 6px;"
						+ "-fx-font-weight: bold;"
						+ "-fx-font-size:20;");
			}
		});
		btn.setOnMouseExited(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				btn.setPrefSize(50,50);
				btn.setStyle("-fx-background-color: transparent; "
						+ "-fx-border-color: blue;"
						+ "-fx-font-family: \"Unispace\"; "
						+ " -fx-border-radius: 30;"
						+ "-fx-text-fill: blue; "
						+ "-fx-border-width: 6px;"
						+ "-fx-font-weight: bold;"
						+ "-fx-font-size:20;");
			}
		});
		
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
