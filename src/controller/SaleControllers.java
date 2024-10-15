package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;

import model.*;
import model.database.*;
import view.*;
import view.templates.Voucher;

public class SaleControllers{
	
	private PurchasePV view;
	
	
	protected double subtotal = 0 , dis = 0;
	protected int i;

	
	private TableViewSelectionModel<Purchase> selectionModel;
	
	
	public SaleControllers(PurchasePV view)
	{
		this.view = view;
		datePickerHandler();
		dateReloadBtnHandler();
		setDataToCbBsupplier();
		setDataToCbBWarehouse();
		setDataToCbBPaymentType();
		itemCodeInputHandler();
		itemNameInputHandler();
		setDataToInvoiceInformation();
		discountPercentHandler();
		setDataToDiscount();
		setDataToSubTotal();
		okBtnHandler();
		cancelBtnHandler();
		purchaseTableHandler();
//		saveButtonHandler();	
	}
	
	public void datePickerHandler() {
		view.getDpPurchaseDate().setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty)
			{
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty ||(date.getYear() >= today.getYear()? (date.getDayOfYear() > today.getDayOfYear() || date.getYear() > today.getYear() ):(date.getYear() > today.getYear())));
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
			if(ItemDAO.existItems(name))
			{
				Item i = ItemDAO.getItems(name);
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
			if(ItemDAO.existItem(code))
			{
				Item i = ItemDAO.getItem(code);
				view.gettName().setText(i.getName());
			}
			else{
				view.gettName().setText("");
			}
		});
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
			setDataToTotal();
		});
	}
	
	public void setDataToDiscount() {
		
		String newtext = view.gettDiscountPercent().getText();
		if(Validation.isNum(newtext))
		{
			double percent = Double.parseDouble(view.gettDiscountPercent().getText());
			
			if(percent <= 100 && percent >= 0)
			{
				dis = subtotal * (percent / 100.0) * 1.0;
				view.getlDiscountResults().setText(dis+"");
			}
		}
	}
	
	public void setDataToSubTotal() {
		subtotal = 0;
		view.getTvPurchases().getItems().forEach(purchase->{
			subtotal += purchase.getQty() * purchase.getPrice();
		});
		view.getlSubTotalResults().setText(subtotal+"");
		setDataToDiscount();
		setDataToTotal();
	}
	
	public void setDataToTotal() {
		view.getlTotalResults().setText(subtotal-dis+"");
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
				Integer qty = Integer.parseInt(view.gettQty().getText());
				Double price = Double.parseDouble(view.gettPrice().getText());
				
				if(ItemDAO.existItem(code))
				{
					view.getTvPurchases().getItems().add(new Purchase(ItemDAO.getItem(code).getId(),qty,price));
					setDataToSubTotal();
				}
				else {
					view.getlErr().setText("Item doesn't exist");
				}
				cleanText();
			}
		});
	}
	
	
	
	public void purchaseTableHandler()
	{
		selectionModel = view.getTvPurchases().getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		view.getTvPurchases().setOnMouseClicked(e->{
			updateInfo();
		});
	}
	
	public void updateInfo()
	{
		
		
		Purchase p = selectionModel.getSelectedItem();
		
		if(p != null)
		{
			String code = ItemDAO.getItem(p.getItem_id()).getCode();
			String name = p.getItem_name();
			Integer qty = p.getQty();
			Double price = p.getPrice();
			
			view.gettCode().setText(code);
			view.gettName().setText(name);
			view.gettQty().setText(qty+"");
			view.gettPrice().setText(price + "");
			
			view.getBtnFP().getChildren().remove(1);
			view.getBtnFP().getChildren().add(view.getBtnUpdate());
			
			view.getBtnUpdate().setOnAction(e->{
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
					
					if(view.gettCode().getText().trim() != code || view.gettName().getText()!= name || view.gettQty().getText().trim() != qty+"" || view.gettPrice().getText().trim() != price+"" )
					{
						if(ItemDAO.existItem(code))
						{
							view.getTvPurchases().getItems().remove(p);
							view.getTvPurchases().getItems().add(new Purchase(ItemDAO.getItem(view.gettCode().getText().trim()).getId(),Integer.parseInt(view.gettQty().getText().trim()),Double.parseDouble(view.gettPrice().getText().trim() )));
							setDataToSubTotal();
							
							cleanText();
							
							view.getBtnFP().getChildren().remove(1);
							view.getBtnFP().getChildren().add(view.getBtnAdd());
						}
						else {
							view.getlErr().setText("Item doesn't exist");
						}
					}	
				}
			});
		}
	}
	
	public void setDataToVoucher() {
		Voucher vc = view.getVoucher();
		
		Supplier s = SupplierDAO.getSupplier(view.getCbBSupplier().getValue());
		vc.getVoucherNo().setText(view.getlInvoiceNoResults().getText());
		vc.getVoucherDate().setText(view.getlInvoiceDateResults().getText());
		vc.getPayment().setText(view.getCbBPaymentType().getValue());
		vc.getName().setText(s.getName());
		vc.getPhone().setText(s.getPhone());
		vc.getAddress().setText(s.getAddress());
		
		i = 1;
		
		view.getTvPurchases().getItems().forEach(p->{

			Label item = new Label(p.getItem_name());
			Label qty = new Label(p.getQty()+"");
			Label price = new Label(p.getPrice()+"");
			Label total = new Label(p.getTotal()+"");
			
			view.getVoucher().getBodyGP().add(item,0,i);
			view.getVoucher().getBodyGP().add(qty,1,i);
			view.getVoucher().getBodyGP().add(price,2,i);
			view.getVoucher().getBodyGP().add(total,3,i);
			i++;
		});
		
		vc.getSubtotal().setText(view.getlSubTotalResults().getText());
		vc.getDiscount().setText(view.getlDiscountResults().getText());
		vc.getAllTotal().setText(view.getlTotalResults().getText());
		vc.getWarehouse().setText(view.getCbBWarehouse().getValue());
		
	}
	
	
	
//	public void saveButtonHandler() {
//		
//		view.getBtnSave().setOnAction(e->{
//			setDataToVoucher();
//			view.createVoucher();
//			voucherHandler();
//		});
//	}
	
	public void cleanText() 
	{
		view.gettCode().setText("");
		view.gettName().setText("");
		view.gettQty().setText("");
		view.gettPrice().setText("");
	}
	
	
	public void voucherHandler() 
	{
		if(view.getAns().isPresent() && view.getAns().get()==ButtonType.OK)
		{
			String invoiceNumber = view.getlInvoiceNoResults().getText();			
			LocalDate invoiceDate = view.getDpPurchaseDate().getValue();
			String payment = view.getCbBPaymentType().getValue();
			double discount = Double.parseDouble(view.getlDiscountResults().getText());
			String people = view.getCbBSupplier().getValue();
			String warehouse = view.getCbBWarehouse().getValue();
			int created_by = 1;
			
			int invoice_id = PurchaseDAO.addVoucher(invoiceNumber, Date.valueOf(invoiceDate), payment, discount, created_by);
			
			view.getTvPurchases().getItems().forEach(p->{
				String code = p.getItem_code();
				String name = p.getItem_name();
				int qty = p.getQty();
				double price = p.getPrice();
				
				PurchaseDAO.addPurchase(invoice_id, people, warehouse, code, name, qty, price, created_by);
				
			});
			
			
			
		}
		
	}
	
	public void reset() {
		view = new PurchasePV();
	}
	

	public PurchasePV getView() {
		return view;
	}
	
}
