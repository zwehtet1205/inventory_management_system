package controller;

import java.util.ArrayList;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;
import model.dao.CustomerDAO;
import model.entities.*;
import view.*;

public class CustomerControllers{
	
	private final CustomerSV view;
	
	private TableViewSelectionModel<Customer> selectionModel;
	
	
	public CustomerControllers(CustomerSV view)
	{
		this.view = view;
		setDataToSupplierTB();
		okBtnHandler();
		cancelBtnHandler();
		customerTableHandler();
		
	}
	
	
	public void setDataToSupplierTB() {
		ArrayList<Customer> al = CustomerDAO.getAll();
		view.getTvCustomer().getItems().addAll(al);
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
				view.getlErr().setText("Please fill customer name");
				
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
				
				if(CustomerDAO.exist(name))
				{
					view.getlErr().setText("Customer already exist");
				}
				else {
					CustomerDAO.add(name, email, phone, address, status);
					view.getTvCustomer().getItems().add(CustomerDAO.get(name));
					view.getlErr().setText("");
				}
				cleanText();
			}
		});
	}
	
	
	
	public void customerTableHandler()
	{
		selectionModel = view.getTvCustomer().getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		view.getTvCustomer().setOnMouseClicked(e->{
			updateInfo();
		});
	}
	
	public void updateInfo()
	{
		
		
		Customer c = selectionModel.getSelectedItem();
		
		if(c != null)
		{
			String name = c.getName();
			String email = c.getEmail();
			String phone = c.getPhone();
			String address = c.getAddress();
			int status = c.getStatus();
			
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
					view.getlErr().setText("Please fill customer name");
					
				}
				else {
					
					if(view.gettName().getText()!= name || view.gettEmail().getText() != email ||view.gettPhone().getText() != phone ||view.gettAAddress().getText() != address || ((view.getcBStatus().isSelected() && status != 1 ) || (!view.getcBStatus().isSelected() && status == 1)))
					{
						
						int sts;
						if(view.getcBStatus().isSelected())
							sts = 1;
						else
							sts = 0;
						CustomerDAO.update(c.getId(), view.gettName().getText(), view.gettEmail().getText(), view.gettPhone().getText(), view.gettAAddress().getText(), sts);
						view.getTvCustomer().getItems().remove(c);
						view.getTvCustomer().getItems().add(CustomerDAO.get(view.gettName().getText()));
						
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
	

	public CustomerSV getView() {
		return view;
	}
}
