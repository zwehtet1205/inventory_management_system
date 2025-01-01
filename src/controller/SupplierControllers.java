package controller;

import java.util.ArrayList;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;
import model.dao.SupplierDAO;
import model.entities.*;
import view.*;

public class SupplierControllers{
	
	private final SupplierPV view;
	
	private TableViewSelectionModel<Supplier> selectionModel;
	
	
	public SupplierControllers(SupplierPV view)
	{
		this.view = view;
		setDataToSupplierTB();
		okBtnHandler();
		cancelBtnHandler();
		categoryTableHandler();
		
	}
	
	
	public void setDataToSupplierTB() {
		ArrayList<Supplier> al = SupplierDAO.getAllSupplier();
		view.getTvSupplier().getItems().addAll(al);
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
				view.getlErr().setText("Please fill Supplier  name");
				
			}
			else {
				
				
				String name = view.gettName().getText();
				String email = view.gettEmail().getText();
				String phone = view.gettPhone().getText();
				String address = view.gettAAddress().getText();
				int status;
				if(view.getcBStatus().isSelected())
					status = 1;
				else
					status = 0;
				
				if(SupplierDAO.existSupplier(name))
				{
					view.getlErr().setText("supplier already exist");
				}
				else {
					SupplierDAO.addSupplierDAO(name, email, phone, address, status);
					view.getTvSupplier().getItems().add(SupplierDAO.getSupplier(name));
					view.getlErr().setText("");
				}
				cleanText();
			}
		});
	}
	
	
	
	public void categoryTableHandler()
	{
		selectionModel = view.getTvSupplier().getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		view.getTvSupplier().setOnMouseClicked(e->{
			updateInfo();
		});
	}
	
	public void updateInfo()
	{
		
		
		Supplier s = selectionModel.getSelectedItem();
		
		if(s!= null)
		{
			String name = s.getName();
			String email = s.getEmail();
			String phone = s.getPhone();
			String address = s.getAddress();
			int status = s.getStatus();
			
			view.gettName().setText(name);
			view.gettEmail().setText(email);
			view.gettPhone().setText(phone);
			view.gettAAddress().setText(address);
			if(status==1)
				view.getcBStatus().setSelected(true);
			else 
				view.getcBStatus().setSelected(false);
			
			view.getBtnFP().getChildren().remove(1);
			view.getBtnFP().getChildren().add(view.getBtnUpdate());
			
			view.getBtnUpdate().setOnAction(e->{
				if(view.gettName().getText().equals(""))
				{
					view.getlErr().setText("Please fill supplier name");
					
				}
				else {
					
					if(view.gettName().getText()!= name || view.gettEmail().getText() != email ||view.gettPhone().getText() != phone ||view.gettAAddress().getText() != address || ((view.getcBStatus().isSelected() && status != 1 ) || (!view.getcBStatus().isSelected() && status == 1)))
					{
						
						int sts;
						if(view.getcBStatus().isSelected())
							sts = 1;
						else
							sts = 0;
						SupplierDAO.updateSupplierDAO(s.getId(), view.gettName().getText(), view.gettEmail().getText(), view.gettPhone().getText(), view.gettAAddress().getText(), sts);
						view.getTvSupplier().getItems().remove(s);
						view.getTvSupplier().getItems().add(SupplierDAO.getSupplier(view.gettName().getText()));
						
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
		view.gettEmail().setText("");
		view.gettPhone().setText("");
		view.gettAAddress().setText("");
	}
	

	public SupplierPV getView() {
		return view;
	}
}
