package controller;


import javafx.scene.layout.HBox;
import view.*;

public class AddNewItemAndUpdateControllers{
	
	private final AddNewItemAndUpdateIV view;
	private HBox categoryHB,itemHB,setPriceHB;
	
	
	public AddNewItemAndUpdateControllers(AddNewItemAndUpdateIV view)
	{
		this.view = view;
		categoryHB = new AddCategoryControllers(new AddCategoryIV()).getView().getContent();
		itemHB = new AddItemControllers(new AddNewItemIV()).getView().getContent();
		setPriceHB = new SetPriceControllers(new SetPriceIV()).getView().getContent();
		addItemMenuHandler();
		addCategoryMenuHandler();
		setPriceMenuHandler();
		
	}
	
	public void addItemMenuHandler() {
		view.getlAddItemM().setOnMouseClicked(e->{
			view.getContent().getChildren().remove(1);
			view.getContent().getChildren().add(itemHB);
		});
	}
	
	public void addCategoryMenuHandler() {
		view.getlAddCategoryM().setOnMouseClicked(e->{
			view.getContent().getChildren().remove(1);
			view.getContent().getChildren().add(categoryHB);
		});
	}
	
	public void setPriceMenuHandler() {
		view.getlSetPriceM().setOnMouseClicked(e->{
			view.getContent().getChildren().remove(1);
			view.getContent().getChildren().add(setPriceHB);
		});
	}
	

	public AddNewItemAndUpdateIV getView() {
		return view;
	}
}
