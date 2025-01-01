package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.DateCell;
import javafx.scene.layout.VBox;
import model.dao.*;
import model.entities.StockDetail;
import view.StockListsIV;


public class StockListControllers {

	private final StockListsIV view;

	public StockListControllers(StockListsIV view) {
		super();
		this.view = view;
		setDataToCbBCategory();
		setDataToCbBWarehouse();
		datePickerStartHandler();
		datePickerEndHandler();
		searchBtnHandler();
		
	}
	
	public VBox getContent() {
		return view.getContent();
	}
	
	public void setDataToCbBCategory()
	{
		ArrayList<String> al = CategoryDAO.getAllCategoryName();
		view.getCbBCategory().getItems().addAll(FXCollections.observableArrayList(al));
		view.getCbBCategory().getSelectionModel().select(0);
		
	}
	
	public void setDataToCbBWarehouse()
	{
		ArrayList<String> al = WarehouseDAO.getAllWarehouseName();
		view.getCbBWarehouse().getItems().addAll(FXCollections.observableArrayList(al));
		view.getCbBWarehouse().getSelectionModel().select(0);
		
	}
	
	public void datePickerStartHandler() {
		view.getDpStartDate().setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty)
			{
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty ||(date.getYear() >= today.getYear()? (date.getDayOfYear() > today.getDayOfYear() || date.getYear() > today.getYear() || date.getDayOfYear() > view.getDpEndDate().getValue().getDayOfYear() ):(date.getYear() > today.getYear()||date.getDayOfYear() > view.getDpEndDate().getValue().getDayOfYear())));
			}
		});
		view.getDpStartDate().setValue(LocalDate.now());	
	}
	
	
	public void datePickerEndHandler() {
		view.getDpEndDate().setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty)
			{
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty ||(date.getYear() >= today.getYear()? (date.getDayOfYear() > today.getDayOfYear() || date.getYear() > today.getYear() || date.getDayOfYear() < view.getDpStartDate().getValue().getDayOfYear()  ):(date.getYear() > today.getYear() || date.getDayOfYear() < view.getDpStartDate().getValue().getDayOfYear())));
			}
		});
		view.getDpEndDate().setValue(LocalDate.now());	
	}
	
	public void searchBtnHandler() {
		view.getBtnSearch().setOnAction(e->{
			
			view.getTvStockList().getItems().remove(0, view.getTvStockList().getItems().size());
			
			String categoryName = view.getCbBCategory().getValue();
			String warehouseName = view.getCbBWarehouse().getValue();
			Date startDate = Date.valueOf(view.getDpStartDate().getValue());
			Date endDate = Date.valueOf(view.getDpEndDate().getValue().plusDays(1));
			
			ArrayList<StockDetail> al = StockDetailDAO.getStockDetails(categoryName,warehouseName,startDate,endDate);
			view.getTvStockList().getItems().addAll(al);
			
		});
	}
	
}
