package controller;

import java.util.ArrayList;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;
import model.dao.WarehouseDAO;
import model.entities.*;
import view.*;

public class WarehouseControllers{
	
	private final WarehouseIV view;
	
	private TableViewSelectionModel<Warehouse> selectionModel;
	
	
	public WarehouseControllers(WarehouseIV view)
	{
		this.view = view;
		setDataToWarehouseTB();
		okBtnHandler();
		cancelBtnHandler();
		wareTableHandler();
		
	}
	
	
	public void setDataToWarehouseTB() {
		ArrayList<Warehouse> al = WarehouseDAO.getAllWarehouse();
		view.getTvWarehouse().getItems().addAll(al);
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
				view.getlErr().setText("Please fill warehouse name");
				
			}
			else if(view.gettALocation().getText().equals(""))
			{
				view.getlErr().setText("Please fill warehouse location");
				
			}
			else {
				
				
				String name = view.gettName().getText();
				String location = view.gettALocation().getText();
				int status;
				if(view.getcBStatus().isSelected())
					status = 1;
				else
					status = 0;
				
				if(WarehouseDAO.existWarehouse(name))
				{
					view.getlErr().setText("Warehouse already exist");
				}
				else {
					WarehouseDAO.addWarehouseDAO(name,location,status);
					view.getTvWarehouse().getItems().add(WarehouseDAO.getWarehouse(name));
					view.getlErr().setText("");
				}
				cleanText();
			}
		});
	}
	
	
	
	public void wareTableHandler()
	{
		selectionModel = view.getTvWarehouse().getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		view.getTvWarehouse().setOnMouseClicked(e->{
			updateInfo();
		});
	}
	
	public void updateInfo()
	{
		
		
		Warehouse w = selectionModel.getSelectedItem();
		
		if(w!= null)
		{
			String name = w.getName();
			String location = w.getLocation();
			int status = w.getStatus();
			
			view.gettName().setText(name);
			view.gettALocation().setText(location);
			if(status==1)
				view.getcBStatus().setSelected(true);
			else 
				view.getcBStatus().setSelected(false);
			
			view.getBtnFP().getChildren().remove(1);
			view.getBtnFP().getChildren().add(view.getBtnUpdate());
			
			view.getBtnUpdate().setOnAction(e->{
				if(view.gettName().getText().equals(""))
				{
					view.getlErr().setText("Please fill warehouse name");
					
				}
				else if(view.gettALocation().getText().equals(""))
				{
					view.getlErr().setText("Please fill warehouse location");
					
				}
				else {
					
					if(view.gettName().getText()!= name || view.gettALocation().getText() != location || ((view.getcBStatus().isSelected() && status != 1 ) || (!view.getcBStatus().isSelected() && status == 1)))
					{
						
						int s;
						if(view.getcBStatus().isSelected())
							s = 1;
						else
							s = 0;
						WarehouseDAO.updateWarehouseDAO(w.getId(), view.gettName().getText(), view.gettALocation().getText(), s);
						view.getTvWarehouse().getItems().remove(w);
						view.getTvWarehouse().getItems().add(WarehouseDAO.getWarehouse(view.gettName().getText()));
						
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
		view.gettALocation().setText("");
	}
	

	public WarehouseIV getView() {
		return view;
	}
}
