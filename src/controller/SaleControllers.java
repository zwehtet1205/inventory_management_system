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
import model.dao.*;
import view.*;
import view.templates.Voucher;

public class SaleControllers{
	
	private SaleSV view;
	
	protected double subtotal = 0 , dis = 0;
	protected int i,remainingQty = 0;
	
	
	private TableViewSelectionModel<Sale> selectionModel;
	
	
	public SaleControllers(SaleSV view)
	{
		this.view = view;
		datePickerHandler();
		dateReloadBtnHandler();
		setDataToCbBsupplier();
		setDataToCbBWarehouse();
		setDataToCbBPaymentType();
		itemCodeInputHandler();
		itemNameInputHandler();
		itemQtyInputHandler();
		setDataToInvoiceInformation();
		discountPercentHandler();
		setDataToDiscount();
		setDataToSubTotal();
		okBtnHandler();
		cancelBtnHandler();
		saleTableHandler();
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
		ArrayList<String> al = CustomerDAO.getAllName();
		view.getCbBSupplier().getItems().addAll(FXCollections.observableArrayList(al));
		view.getCbBSupplier().getSelectionModel().select(0);
	}
	
	public void setDataToCbBWarehouse()
	{
		ArrayList<String> al = WarehouseDAO.getAllWarehouseName();
		view.getCbBWarehouse().getItems().addAll(FXCollections.observableArrayList(al));
		view.getCbBWarehouse().getSelectionModel().select(0);
	}
	
	public void setDataToCbBPaymentType()
	{
		ArrayList<String> al = PaymentDAO.getAllPaymentType();
		view.getCbBPaymentType().getItems().addAll(FXCollections.observableArrayList(al));
		view.getCbBPaymentType().getSelectionModel().select(0);
	}
	
	public void itemNameInputHandler()
	{
		view.gettName().setOnKeyTyped(e->{
			
			String name = view.gettName().getText();
			if(ItemDAO.existItems(name))
			{
				Product i = ItemDAO.getItems(name);
				view.gettCode().setText(i.getCode());
				
				remainingQty = ItemDAO.getRemainingQty(WarehouseDAO.getWarehouse(view.getCbBWarehouse().getValue()).getId(), i.getId());
				
				view.getlItemQty().setText(remainingQty+"");
				view.getlItemPriceResult().setText(i.getPrice()+"");
			}
			else{
				
				view.gettCode().setText("");
				remainingQty =  0 ;
				view.getlItemQty().setText("0");
				view.getlItemPriceResult().setText("0");
			}
		});
	}
	
	public void itemCodeInputHandler()
	{
		view.gettCode().setOnKeyTyped(e->{
			
			String code = view.gettCode().getText();
			if(ItemDAO.existItem(code))
			{
				Product i = ItemDAO.getItem(code);
				view.gettName().setText(i.getName());
				
				remainingQty = ItemDAO.getRemainingQty(WarehouseDAO.getWarehouse(view.getCbBWarehouse().getValue()).getId(), i.getId());
				
				view.getlItemQty().setText(remainingQty+"");
				view.getlItemPriceResult().setText(i.getPrice()+"");
			}
			else{
				view.gettName().setText("");
				remainingQty =  0 ;
				view.getlItemQty().setText("0");
				view.getlItemPriceResult().setText("0");
			}
		});
	}
	
	public void itemQtyInputHandler() {
		view.gettQty().setOnKeyTyped(e->{
			int q = 0;
			
			String qty = view.gettQty().getText();
			
			if(qty.trim() == "") {
				q = 0;
				view.getlErr().setText("");
			}
			else if(!Validation.isNum(qty))
			{
				view.getlErr().setText("Qty must be number");
			} else {
				q = Integer.parseInt(qty);
								
			}
			if(remainingQty != 0) {
				int itemqty = remainingQty;
				view.getlItemQty().setText((itemqty-q)+"");
				
				if(q > itemqty) {
					view.getlErr().setText("Qty is not enough");
				} else {
					view.getlErr().setText("");
				}
			} 
			
			
		});
	}
	
	

	public void setDataToInvoiceInformation()
	{
		view.getlInvoiceNoResults().setText("SA"+LocalDate.now().getYear()+InvoiceDAO.getNoOfSales());
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
		view.getTvSales().getItems().forEach(purchase->{
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
			else {
				
				String code = view.gettCode().getText();
				Integer qty = Integer.parseInt(view.gettQty().getText());
				
				
				if(ItemDAO.existItem(code))
				{
					Product i = ItemDAO.getItem(code);
					view.getTvSales().getItems().add(new Sale(i.getId(),qty));
					setDataToSubTotal();
				}
				else {
					view.getlErr().setText("Item doesn't exist");
				}
				cleanText();
			}
		});
	}
	
	
	
	public void saleTableHandler()
	{
		selectionModel = view.getTvSales().getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		view.getTvSales().setOnMouseClicked(e->{
			updateInfo();
		});
	}
	
	public void updateInfo()
	{
		
		
		Sale s = selectionModel.getSelectedItem();
		
		if(s != null)
		{
			Product i = ItemDAO.getItem(s.getItem_id());
			String code = i.getCode();
			String name = s.getItem_name();
			Integer qty = s.getQty();
			Double price = s.getPrice();
			
			view.gettCode().setText(code);
			view.gettName().setText(name);
			view.gettQty().setText(qty+"");
			
			remainingQty = ItemDAO.getRemainingQty(WarehouseDAO.getWarehouse(view.getCbBWarehouse().getValue()).getId(), i.getId());
			view.getlItemQty().setText(remainingQty-qty+"");
			
			view.getlItemPriceResult().setText(price + "");
			
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
				else {
					
					if(view.gettCode().getText().trim() != code || view.gettName().getText()!= name || view.gettQty().getText().trim() != qty+""  )
					{
						if(ItemDAO.existItem(code))
						{
							view.getTvSales().getItems().remove(s);
							view.getTvSales().getItems().add(new Sale(ItemDAO.getItem(view.gettCode().getText().trim()).getId(),Integer.parseInt(view.gettQty().getText().trim())));
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
		
		Customer c = CustomerDAO.get(view.getCbBSupplier().getValue());
		vc.getVoucherNo().setText(view.getlInvoiceNoResults().getText());
		vc.getVoucherDate().setText(view.getlInvoiceDateResults().getText());
		vc.getPayment().setText(view.getCbBPaymentType().getValue());
		vc.getName().setText(c.getName());
		vc.getPhone().setText(c.getPhone());
		vc.getAddress().setText(c.getAddress());
		
		i = 1;
		
		view.getTvSales().getItems().forEach(p->{

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
	
	
	
	public SaleControllers saveButtonHandler() {
		setDataToVoucher();
		view.createVoucher();
		voucherHandler();
		return new SaleControllers(new SaleSV());
		
	}
	
	public void cleanText() 
	{
		view.gettCode().setText("");
		view.gettName().setText("");
		view.gettQty().setText("");
		remainingQty = 0;
		view.getlItemQty().setText("0");
		view.getlItemPriceResult().setText("0");
	}
	
	
	public void voucherHandler() 
	{
		if(view.getAns().isPresent() && view.getAns().get()==ButtonType.OK)
		{
			String invoiceNumber = view.getlInvoiceNoResults().getText();			
			Date invoiceDate = Date.valueOf(view.getDpPurchaseDate().getValue());
			String payment = view.getCbBPaymentType().getValue();
			double discount = Double.parseDouble(view.getlDiscountResults().getText());
			String people = view.getCbBSupplier().getValue();
			String warehouse = view.getCbBWarehouse().getValue();
			int created_by = 1;
			
			int invoice_id = InvoiceDAO.addVoucher(invoiceNumber,invoiceDate,1, payment, discount, created_by);
			
			view.getTvSales().getItems().forEach(s->{
				String code = s.getItem_code();
				String name = s.getItem_name();
				int qty = s.getQty();
				
				SaleDAO.add(invoice_id, people, warehouse, code, name, qty, created_by);
				
			});
			
			
			
		}
		
	}
	
//	public void reset() {
//		view = new PurchasePV();
//	}
	

	public SaleSV getView() {
		return view;
	}
	
}
