package controller;

import java.util.ArrayList;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;

import model.*;
import view.*;

public class AddCategoryControllers{
	
	private final AddCategoryIV view;
	
	private TableViewSelectionModel<Category> selectionModel;
	
	
	public AddCategoryControllers(AddCategoryIV view)
	{
		this.view = view;
		setDataToCategoryTB();
		okBtnHandler();
		cancelBtnHandler();
		categoryTableHandler();
		
	}
	
	
	public void setDataToCategoryTB() {
		ArrayList<Category> al = DBHandler.getAllCategory();
		view.getTvCategories().getItems().addAll(al);
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
	


	public void okBtnHandler()
	{
		view.getBtnAdd().setOnAction(e->{
			if(view.gettName().getText().equals(""))
			{
				view.getlErr().setText("Please fill category name");
				
			}
			else if(view.gettADescription().getText().equals(""))
			{
				view.getlErr().setText("Please fill category description");
				
			}
			else {
				
				
				String name = view.gettName().getText();
				String description = view.gettADescription().getText();
				int status;
				if(view.getcBStatus().isSelected())
					status = 1;
				else
					status = 0;
				
				if(DBHandler.existCategorys(name))
				{
					view.getlErr().setText("Category already exist");
				}
				else {
					DBHandler.addCategoryDAO(name,description,status);
					view.getTvCategories().getItems().add(DBHandler.getCategory(name));
					view.getlErr().setText("");
				}
				cleanText();
			}
		});
	}
	
	
	
	public void categoryTableHandler()
	{
		selectionModel = view.getTvCategories().getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		view.getTvCategories().setOnMouseClicked(e->{
			updateInfo();
		});
	}
	
	public void updateInfo()
	{
		
		
		Category c = selectionModel.getSelectedItem();
		
		if(c!= null)
		{
			String name = c.getName();
			String description = c.getDescription();
			int status = c.getStatus();
			
			view.gettName().setText(name);
			view.gettADescription().setText(description);
			if(status==1)
				view.getcBStatus().setSelected(true);
			else 
				view.getcBStatus().setSelected(false);
			
			view.getBtnFP().getChildren().remove(1);
			view.getBtnFP().getChildren().add(view.getBtnUpdate());
			
			view.getBtnUpdate().setOnAction(e->{
				if(view.gettName().getText().equals(""))
				{
					view.getlErr().setText("Please fill category name");
					
				}
				else if(view.gettADescription().getText().equals(""))
				{
					view.getlErr().setText("Please fill category description");
					
				}
				else {
					
					if(view.gettName().getText()!= name || view.gettADescription().getText() != description || ((view.getcBStatus().isSelected() && status != 1 ) || (!view.getcBStatus().isSelected() && status == 1)))
					{
						
						int s;
						if(view.getcBStatus().isSelected())
							s = 1;
						else
							s = 0;
						DBHandler.updateCategoryDAO(c.getId(), view.gettName().getText(), view.gettADescription().getText(), s);
						view.getTvCategories().getItems().remove(c);
						view.getTvCategories().getItems().add(DBHandler.getCategory(view.gettName().getText()));
						
					}
					cleanText();
					
					view.getBtnFP().getChildren().remove(1);
					view.getBtnFP().getChildren().add(view.getBtnAdd());
					view.getlErr().setText("");
					
				}
			});
		}
		
		
		
	}
	
	public void cleanText() 
	{

		view.gettName().setText("");
		view.gettADescription().setText("");
	}
	

	public AddCategoryIV getView() {
		return view;
	}
}
