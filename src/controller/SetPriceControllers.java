package controller;


import java.util.ArrayList;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;

import model.*;
import view.*;

public class SetPriceControllers{
	
	private final SetPriceIV view;
	
	private TableViewSelectionModel<Item> selectionModel;
	
	
	public SetPriceControllers(SetPriceIV view)
	{
		this.view = view;
		setDataToItemTB();
		codeInputHandler();
		cancelBtnHandler();
		setBtnHandler();
		categoryTableHandler();
		
	}
	
	
	public void setDataToItemTB() {
		ArrayList<Item> al = DBHandler.getAllItems();
		view.getTvItems().getItems().addAll(al);
	} 
	
	public void cancelBtnHandler()
	{
		view.getBtnCancel().setOnAction(e->{
			cleanText();
			view.getlErr().setText("");
		});
	}
	
	public void codeInputHandler()
	{
		view.gettCode().setOnKeyReleased(e->{
			String code = view.gettCode().getText();
			if(DBHandler.existItem(code))
			{
				view.getlNameResult().setText(DBHandler.getItem(code).getName());
				view.gettPrice().setText(DBHandler.getItem(code).getPrice()+"");
			}
			else {
				view.getlNameResult().setText("");
				view.gettPrice().setText("");
			}
		});
	}
	public void setBtnHandler()
	{
		view.getBtnSet().setOnAction(e->{
			if(view.gettCode().getText().equals(""))
				view.getlErr().setText("Please fill item code");
			else if(view.gettPrice().getText().equals(""))
				view.getlErr().setText("Please fill item price");
			else 
			{	
				String code = view.gettCode().getText();
				try {
					double price = Double.parseDouble(view.gettPrice().getText());
					if(!(DBHandler.existItem(code)))
					{
						view.getlErr().setText("Item doesn't exist");
					}
					else 
					{
						DBHandler.updatePrice(code, price);
						view.getTvItems().getItems().add(DBHandler.getItem(code));
						view.getlErr().setText("");
					}
					cleanText();
				}
				catch(NumberFormatException ex)
				{
					
					view.getlErr().setText("Please fill price as number");
				}
				
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
			double price = i.getPrice();
			
			view.getlNameResult().setText(name);
			view.gettCode().setText(code);
			view.gettPrice().setText(price+"");
		}
		
	}
	
	public void cleanText() 
	{
		view.getlNameResult().setText("");
		view.gettCode().setText("");
		
		view.gettPrice().setText("");
	}
	

	public SetPriceIV getView() {
		return view;
	}
}
