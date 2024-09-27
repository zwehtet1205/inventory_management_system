package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;

import model.*;
import view.*;

public class AddItemControllers{
	
	private final AddNewItemIV view;
	
	private TableViewSelectionModel<Item> selectionModel;
	
	
	public AddItemControllers(AddNewItemIV view)
	{
		this.view = view;
		setDataToCategoryTB();
		setDatToCategoryComboBox();
		cancelBtnHandler();
		okBtnHandler();
		categoryTableHandler();
		
	}
	
	
	public void setDataToCategoryTB() {
		ArrayList<Item> al = DBHandler.getAllItems();
		view.getTvItems().getItems().addAll(al);
	} 
	
	public void cancelBtnHandler()
	{
		view.getBtnCancel().setOnAction(e->{
			cleanText();
			view.getlErr().setText("");
			if(!view.getBtnFP().getChildren().contains(view.getBtnAdd()))
			{
				view.getBtnFP().getChildren().remove(1);
				view.getBtnFP().getChildren().add(view.getBtnAdd());
			}
		});
	}
	
	public void setDatToCategoryComboBox()
	{
		ArrayList<String> al = DBHandler.getAllCategoryName();
		view.getCbBCategory().getItems().addAll(FXCollections.observableArrayList(al));
		view.getCbBCategory().getSelectionModel().select(0);
	}
	


	public void okBtnHandler()
	{
		view.getBtnAdd().setOnAction(e->{
			if(view.gettCode().getText().equals(""))
				view.getlErr().setText("Please fill item code");
			else if(view.gettName().getText().equals(""))
				view.getlErr().setText("Please fill item name");
				
			else 
			{	
				String code = view.gettCode().getText();
				String name = view.gettName().getText();
				String category = view.getCbBCategory().getValue();
				
				int status;
				if(view.getChBStatus().isSelected())
					status = 1;
				else
					status = 0;
	

				if(DBHandler.existItems(name))
				{
					view.getlErr().setText("Category already exist");
				}
				else 
				{
					DBHandler.addItemsDAO(code,name,category,status);
					view.getTvItems().getItems().add(DBHandler.getItem(code));
					view.getlErr().setText("");
				}
				cleanText();
			}
		});
	}
	
	
	
	public void categoryTableHandler()
	{
		selectionModel = view.getTvItems().getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		view.getTvItems().setOnMouseClicked(e->{
			updateInfo();
		});
	}
	
	public void updateInfo()
	{
		
		
		Item i = selectionModel.getSelectedItem();
		
		if(i != null)
		{
			String code = i.getCode();
			String name = i.getName();
			String category = i.getCategory();
			int status = i.getStatus();
			
			view.gettCode().setText(code);
			view.gettName().setText(name);
			view.getCbBCategory().getSelectionModel().select(category);
			if(status==1)
				view.getChBStatus().setSelected(true);
			else 
				view.getChBStatus().setSelected(false);
			
			view.getBtnFP().getChildren().remove(1);
			view.getBtnFP().getChildren().add(view.getBtnUpdate());
			
			view.getBtnUpdate().setOnAction(e->{
				if(view.gettCode().getText().equals(""))
					view.getlErr().setText("Please fill item code");
				else if(view.gettName().getText().equals(""))
					view.getlErr().setText("Please fill item name");
				else {
					
					if(view.gettCode().getText()!= code ||view.gettName().getText()!= name || view.getCbBCategory().getValue()!= category|| ((view.getChBStatus().isSelected() && status != 1 ) || (!view.getChBStatus().isSelected() && status == 1)))
					{
						
						int s;
						if(view.getChBStatus().isSelected())
							s = 1;
						else
							s = 0;
						DBHandler.updateItemDAO(i.getId(), view.gettCode().getText(),view.gettName().getText(), view.getCbBCategory().getValue(), s);
						view.getTvItems().getItems().remove(i);
						view.getTvItems().getItems().add(DBHandler.getItem(view.gettName().getText()));
					
					}
				
					cleanText();
					view.getBtnFP().getChildren().remove(1);
					view.getBtnFP().getChildren().add(view.getBtnAdd());
				
				}
			});
		}
		
		
		
	}
	
	public void cleanText() 
	{
		view.gettCode().setText("");
		view.gettName().setText("");
		view.getCbBCategory().getSelectionModel().select(0);
	}
	

	public AddNewItemIV getView() {
		return view;
	}
}
