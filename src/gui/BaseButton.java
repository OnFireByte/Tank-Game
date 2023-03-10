package gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;

public class BaseButton extends Button {

	public BaseButton(String name) {
		this(name, 200, 50);
	}

	public BaseButton(String name, int fontSize) {
		this(name, 200, 50, fontSize);
	}

	public BaseButton(String name, int w, int h) {
		this(name, w, h, 15);
	}

	public BaseButton(String name, int w, int h, int fontSize) {

		super(name);
		this.setFont(RenderableHolder.getFont(fontSize));
		this.setTextFill(Color.AZURE);
		ImageView b = RenderableHolder.getButton(w, h);
		ImageView bp = RenderableHolder.getButtonPressed(w - 3, h - 3);
		this.setBackground(null);
		this.setPadding(Insets.EMPTY);
		this.setContentDisplay(ContentDisplay.CENTER);
		this.setGraphic(b);

		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				setGraphic(bp);
				setCursor(Cursor.HAND);
			}
		});
		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				setGraphic(b);
				setCursor(Cursor.DEFAULT);

			}
		});
	}

}