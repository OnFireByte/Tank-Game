package gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;


	public BaseButton(String name) {
		this(name, 20);
	}

	public BaseButton(String name, int fontSize) {

		super(name);
		this.setPrefHeight(50);
		this.setPrefWidth(300);
		this.setStyle("-fx-background-color: darkblue; "
				+ "-fx-border-color: blue; "
				+ "-fx-text-fill: white; "
				+ "-fx-border-width: 5px;");
		setFont(RenderableHolder.getFont(fontSize));

		this.setOnMouseEntered(e -> {
			setPrefHeight(55);
			setPrefWidth(320);
			setStyle("-fx-background-color: blue; "
					+ "-fx-border-color: black; "
					+ "-fx-text-fill: white; "
					+ "-fx-border-width: 5px;");

		});
		this.setOnMouseExited(e -> {
			setPrefHeight(50);
			setPrefWidth(300);
			setStyle("-fx-background-color: darkblue; "
					+ "-fx-border-color: blue; "
					+ "-fx-text-fill: white; "
					+ "-fx-border-width: 5px;");
		});
	}

}
