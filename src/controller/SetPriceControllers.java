package controller;


import java.util.ArrayList;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;
import model.*;
import model.dao.*;
import view.*;

public class SetPriceControllers{
	
	private final SetPriceIV view;
	
	private TableViewSelectionModel<Product> selectionModel;
	
	
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
		ArrayList<Product> al = ItemDAO.getAllItems();
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
			if(ItemDAO.existItem(code))
			{
				view.getlNameResult().setText(ItemDAO.getItem(code).getName());
				view.gettPrice().setText(ItemDAO.getItem(code).getPrice()+"");
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
					if(!(ItemDAO.existItem(code)))
					{
						view.getlErr().setText("Item doesn't exist");
					}
					else 
					{
						ItemDAO.updatePrice(code, price);
						view.getTvItems().getItems().add(ItemDAO.getItem(code));
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
		
		
		Product i = selectionModel.getSelectedItem();
		
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
