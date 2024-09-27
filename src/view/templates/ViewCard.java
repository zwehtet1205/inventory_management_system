package view.templates;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class ViewCard {
	
	
	private VBox card;
	
	private String borderColor;
	
	public ViewCard() {
		super();
		card = new VBox(20);

		card.getStyleClass().add("card");
		
	}

	public ViewCard(Label cardTitle) {
		super();
		card = new VBox(20);
		card.getChildren().add(cardTitle);
		
		card.getStyleClass().add("card");
		cardTitle.getStyleClass().add("card-title");
		
	}
	
	

	public VBox getCard() {
		return card;
	}

	public void setCard(VBox card) {
		this.card = card;
	}
	
	public void add(Node n)
	{
		this.card.getChildren().add(n);
	}

	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
		card.setStyle("-fx-border-color:transparent transparent transparent "+borderColor+";");
	}
	
	

}
