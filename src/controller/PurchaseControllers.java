package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.DateCell;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;

import model.*;
import view.*;

public class PurchaseControllers{
	
	private final PurchasePV view;
	
//	private TableViewSelectionModel<Supplier> selectionModel;
	
	
	public PurchaseControllers(PurchasePV view)
	{
		this.view = view;
		datePickerHandler();
		dateReloadBtnHandler();
		setDataToCbBsupplier();
		setDataToCbBWarehouse();
		setDataToCbBPaymentType();
		itemCodeInputHandler();
		itemNameInputHandler();
		setDataToPurchaseTB();
		setDataToInvoiceInformation();
		discountPercentHandler();
		setDataToDiscount();
		okBtnHandler();
//		cancelBtnHandler();
//		categoryTableHandler();
		
	}
	
	public void datePickerHandler() {
		view.getDpPurchaseDate().setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty)
			{
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty || date.getDayOfMonth() > today.getDayOfMonth() || date.getMonthValue() > today.getMonthValue() || date.getYear() > today.getYear());
			}
		});
		view.getDpPurchaseDate().setValue(LocalDate.now());
		
		
	}
	
	public void dateReloadBtnHandler() {
		view.getBtnDateReload().setOnAction(e->{
			setDataToInvoiceDate();
		});
	}
	public void setDataToCbBsupplier()
	{
		ArrayList<String> al = PurchaseDAO.getAllSupplierName();
		view.getCbBSupplier().getItems().addAll(FXCollections.observableArrayList(al));
		view.getCbBSupplier().getSelectionModel().select(0);
	}
	
	public void setDataToCbBWarehouse()
	{
		ArrayList<String> al = PurchaseDAO.getAllWarehouseName();
		view.getCbBWarehouse().getItems().addAll(FXCollections.observableArrayList(al));
		view.getCbBWarehouse().getSelectionModel().select(0);
	}
	
	public void setDataToCbBPaymentType()
	{
		ArrayList<String> al = PurchaseDAO.getAllPaymentType();
		view.getCbBPaymentType().getItems().addAll(FXCollections.observableArrayList(al));
		view.getCbBPaymentType().getSelectionModel().select(0);
	}
	
	public void itemNameInputHandler()
	{
		view.gettName().setOnKeyTyped(e->{
			
			String name = view.gettName().getText();
			if(DBHandler.existItems(name))
			{
				Item i = DBHandler.getItems(name);
				view.gettCode().setText(i.getCode());
			}
			else{
				view.gettCode().setText("");
			}
		});
	}
	
	public void itemCodeInputHandler()
	{
		view.gettCode().setOnKeyTyped(e->{
			
			String code = view.gettCode().getText();
			if(DBHandler.existItem(code))
			{
				Item i = DBHandler.getItem(code);
				view.gettName().setText(i.getName());
			}
			else{
				view.gettName().setText("");
			}
		});
	}
	
	public void setDataToPurchaseTB() {
		ArrayList<Purchase> al = PurchaseDAO.getAllPurchases();
		view.getTvPurchases().getItems().addAll(al);
	} 
	
	public void setDataToInvoiceInformation()
	{
		view.getlInvoiceNoResults().setText("PC"+LocalDate.now().getYear()+PurchaseDAO.getNoOfInvoices());
		setDataToInvoiceDate();
		view.gettDiscountPercent().setText(0+"");
		
		
	}
	
	public void setDataToInvoiceDate() {
		view.getlInvoiceDateResults().setText(view.getDpPurchaseDate().getValue()+"");
	}
	
	
	public void discountPercentHandler() {
		view.gettDiscountPercent().setOnKeyReleased(e->{
			setDataToDiscount();
		});
	}
	
	public void setDataToDiscount() {
		
		String newtext = view.gettDiscountPercent().getText();
		if(Validation.isNum(newtext))
		{
			double percent = Double.parseDouble(view.gettDiscountPercent().getText());
			double subtotal = 1000000;
			
			if(percent <= 100 && percent >= 0)
			{
				double dis = subtotal * (percent / 100.0) * 1.0;
				view.getlDiscountResults().setText(dis+"");
			}
		}
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
			if(view.gettCode().getText().equals(""))
			{
				view.getlErr().setText("Please fill Item Code");
				
			}
			else if(view.gettName().getText().equals(""))
			{
				view.getlErr().setText("Please fill Item Name");
				
			}
			else if(view.gettQty().getText().equals(""))
			{
				view.getlErr().setText("Please fill Item Qty");
				
			}
			else if(view.gettPrice().getText().equals(""))
			{
				view.getlErr().setText("Please fill Item Price");
				
			}
			else if(!Validation.isNum(view.gettQty().getText()))
			{
				view.getlErr().setText("Qty must be number");
			}
			else if(!Validation.isNum(view.gettPrice().getText()))
			{
				view.getlErr().setText("Price must be number");
			}
			else {
				
				String code = view.gettCode().getText();
				String name = view.gettName().getText();
				Integer qty = Integer.parseInt(view.gettQty().getText());
				Double price = Double.parseDouble(view.gettPrice().getText());
				
				int status;
				if(view.getcBStatus().isSelected())
					status = 1;
				else
					status = 0;
				
				if(DBHandler.existItem(code))
				{
					view.getTvPurchases().getItems().add(PurchaseDAO.getPurchase(PurchaseDAO.addPurchase(code, name, qty, price, status)));
					
				}
				else {
					view.getlErr().setText("Item doesn't exist");
				}
				cleanText();
			}
		});
	}
//	
//	
//	
//	public void categoryTableHandler()
//	{
//		selectionModel = view.getTvSupplier().getSelectionModel();
//		selectionModel.setSelectionMode(SelectionMode.SINGLE);
//		
//		view.getTvSupplier().setOnMouseClicked(e->{
//			updateInfo();
//		});
//	}
//	
//	public void updateInfo()
//	{
//		
//		
//		Supplier s = selectionModel.getSelectedItem();
//		
//		if(s!= null)
//		{
//			String name = s.getName();
//			String email = s.getContact().getEmail();
//			String phone = s.getContact().getPhone();
//			String address = s.getContact().getAddress();
//			int status = s.getStatus();
//			
//			view.gettName().setText(name);
//			view.gettEmail().setText(email);
//			view.gettPhone().setText(phone);
//			view.gettAAddress().setText(address);
//			if(status==1)
//				view.getcBStatus().setSelected(true);
//			else 
//				view.getcBStatus().setSelected(false);
//			
//			view.getBtnFP().getChildren().remove(1);
//			view.getBtnFP().getChildren().add(view.getBtnUpdate());
//			
//			view.getBtnUpdate().setOnAction(e->{
//				if(view.gettName().getText().equals(""))
//				{
//					view.getlErr().setText("Please fill supplier name");
//					
//				}
//				else {
//					
//					if(view.gettName().getText()!= name || view.gettEmail().getText() != email ||view.gettPhone().getText() != phone ||view.gettAAddress().getText() != address || ((view.getcBStatus().isSelected() && status != 1 ) || (!view.getcBStatus().isSelected() && status == 1)))
//					{
//						
//						int sts;
//						if(view.getcBStatus().isSelected())
//							sts = 1;
//						else
//							sts = 0;
//						DBHandler.updateSupplierDAO(s.getId(), view.gettName().getText(), view.gettEmail().getText(), view.gettPhone().getText(), view.gettAAddress().getText(), sts);
//						view.getTvSupplier().getItems().remove(s);
//						view.getTvSupplier().getItems().add(DBHandler.getSupplier(view.gettName().getText()));
//						
//					}
//					cleanText();
//					
//					view.getBtnFP().getChildren().remove(1);
//					view.getBtnFP().getChildren().add(view.getBtnAdd());
//					view.getlErr().setText("");
//					
//				}
//			});
//		}
//		
//		
//		
//	}
//	
	public void cleanText() 
	{

		view.gettCode().setText("");
		view.gettName().setText("");
		view.gettQty().setText("");
		view.gettPrice().setText("");
		view.getcBStatus().setSelected(true);

	}
	

	public PurchasePV getView() {
		return view;
	}
}
