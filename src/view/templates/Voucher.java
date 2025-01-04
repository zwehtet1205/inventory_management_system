package view.templates;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Voucher {
	private Label lVoucherNo,voucherNo,lVoucherDate,voucherDate,lPayment,payment,lName,name,lPhone,phone,lAddress,address,
					lItems,lQty,lPrice,lTotal,
					lSubtotal,subtotal,lDiscount,discount,lAllTotal,allTotal,
					lWarehouse,warehouse;
	
	private GridPane headLeftGP,headRightGP,bodyGP,footerGP;
	private HBox headHB;
	private FlowPane footerFP,reportFP;
	private BorderPane report;
	
	public Voucher() {
		createNodes();
		createLayouts();
		setStyles();
	}
	
	public FlowPane getReport()
	{
		return reportFP;
		
	}
	
	public void createNodes() {
		lVoucherNo = new Label("VOUCHER NO.");
		voucherNo = new Label();
		lVoucherDate = new Label("VOUCHER DATE");
		voucherDate = new Label();
		lPayment = new Label("PAYMENT TYPE");
		payment = new Label();
		lName = new Label("NAME");
		name = new Label();
		lPhone = new Label("PHONE NO.");
		phone = new Label("09987654321");
		lAddress = new Label("ADDRESS");
		address = new Label("Mandalay");
		lItems = new Label("ITEMS");
		lQty = new Label("QTY");
		lPrice = new Label("PRICE");
		lTotal = new Label("TOTAL");
		lSubtotal = new Label("SUBTOTAL");
		subtotal = new Label();
		lDiscount = new Label("DISCOUNT");
		discount = new Label();
		lAllTotal = new Label("TOTAL");
		allTotal = new Label();
		lWarehouse = new Label("From :");
		warehouse = new Label();
	}
	
	public void createLayouts() {
		
		report = new BorderPane();
		
		headLeftGP = new GridPane(5,5);
		headLeftGP.add(lVoucherNo, 0, 0);
		headLeftGP.add(new Label(":"), 1, 0);
		headLeftGP.add(voucherNo, 2, 0);
		headLeftGP.add(lVoucherDate, 0, 1);
		headLeftGP.add(new Label(":"), 1, 1);
		headLeftGP.add(voucherDate, 2, 1);
		headLeftGP.add(lPayment, 0, 2);
		headLeftGP.add(new Label(":"), 1, 2);
		headLeftGP.add(payment, 2, 2);
		headLeftGP.setAlignment(Pos.CENTER_LEFT);
		
		headRightGP = new GridPane(5,5);
		headRightGP.add(lName, 0, 0);
		headRightGP.add(new Label(":"), 1, 0);
		headRightGP.add(name, 2, 0);
		headRightGP.add(lPhone, 0, 1);
		headRightGP.add(new Label(":"), 1, 1);
		headRightGP.add(phone, 2, 1);
		headRightGP.add(lAddress, 0, 2);
		headRightGP.add(new Label(":"), 1, 2);
		headRightGP.add(address, 2, 2);
		headRightGP.setAlignment(Pos.CENTER_RIGHT);
		
		headHB = new HBox();
		headHB.setSpacing(10);
		
		Region spacerHbox  = new Region();
		HBox.setHgrow(spacerHbox, Priority.ALWAYS);
		
		
		
		headHB.getChildren().addAll(headLeftGP,spacerHbox,headRightGP);
		
		report.setTop(new VBox(new HBox(5,lWarehouse,warehouse),headHB));
		
			
		
		bodyGP = new GridPane(40,10);
		bodyGP.add(lItems, 0, 0);
		bodyGP.add(lQty, 1, 0);
		bodyGP.add(lPrice, 2, 0);
		bodyGP.add(lTotal, 3, 0);
		bodyGP.setAlignment(Pos.CENTER);
		
		report.setCenter(bodyGP);
		
		footerGP = new GridPane(5,5);
		footerGP.add(lSubtotal, 0, 0);
		footerGP.add(new Label(":"), 1, 0);
		footerGP.add(subtotal, 2, 0);
		footerGP.add(lDiscount, 0, 1);
		footerGP.add(new Label(":"), 1, 1);
		footerGP.add(discount, 2, 1);
		footerGP.add(lAllTotal, 0, 2);
		footerGP.add(new Label(":"), 1, 2);
		footerGP.add(allTotal, 2, 2);
		
		footerFP = new FlowPane(footerGP);
		footerFP.setAlignment(Pos.BASELINE_RIGHT);
		
		report.setBottom(footerFP);
		
		reportFP = new FlowPane(report);
		reportFP.setAlignment(Pos.CENTER);
		
	}
	
	public void setStyles()
	{
		report.getStyleClass().add("voucher");
		
		headHB.getStyleClass().add("voucher-header");
		bodyGP.getStyleClass().add("voucher-body");
		footerFP.getStyleClass().add("voucher-footer");
		lVoucherNo.getStyleClass().add("voucher-title");
		lVoucherDate.getStyleClass().add("voucher-title");
		lPayment.getStyleClass().add("voucher-title");
		lName.getStyleClass().add("voucher-title");
		lPhone.getStyleClass().add("voucher-title");
		lAddress.getStyleClass().add("voucher-title");
		lItems.getStyleClass().add("voucher-title");
		lQty.getStyleClass().add("voucher-title");
		lPrice.getStyleClass().add("voucher-title");
		lTotal.getStyleClass().add("voucher-title");
		lSubtotal.getStyleClass().add("voucher-title");
		lDiscount.getStyleClass().add("voucher-title");
		lAllTotal.getStyleClass().add("voucher-title");
		lWarehouse.getStyleClass().add("voucher-note");
		warehouse.getStyleClass().add("voucher-note");
	}

	public Label getlVoucherNo() {
		return lVoucherNo;
	}

	public void setlVoucherNo(Label lVoucherNo) {
		this.lVoucherNo = lVoucherNo;
	}

	public Label getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(Label voucherNo) {
		this.voucherNo = voucherNo;
	}

	public Label getlVoucherDate() {
		return lVoucherDate;
	}

	public void setlVoucherDate(Label lVoucherDate) {
		this.lVoucherDate = lVoucherDate;
	}

	public Label getVoucherDate() {
		return voucherDate;
	}

	public void setVoucherDate(Label voucherDate) {
		this.voucherDate = voucherDate;
	}

	public Label getlPayment() {
		return lPayment;
	}

	public void setlPayment(Label lPayment) {
		this.lPayment = lPayment;
	}

	public Label getPayment() {
		return payment;
	}

	public Label getlWarehouse() {
		return lWarehouse;
	}

	public void setlWarehouse(Label lWarehouse) {
		this.lWarehouse = lWarehouse;
	}

	public Label getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Label warehouse) {
		this.warehouse = warehouse;
	}

	public void setPayment(Label payment) {
		this.payment = payment;
	}

	public Label getlName() {
		return lName;
	}

	public void setlName(Label lName) {
		this.lName = lName;
	}

	public Label getName() {
		return name;
	}

	public void setName(Label name) {
		this.name = name;
	}

	public Label getlPhone() {
		return lPhone;
	}

	public void setlPhone(Label lPhone) {
		this.lPhone = lPhone;
	}

	public Label getPhone() {
		return phone;
	}

	public void setPhone(Label phone) {
		this.phone = phone;
	}

	public Label getlAddress() {
		return lAddress;
	}

	public void setlAddress(Label lAddress) {
		this.lAddress = lAddress;
	}

	public Label getAddress() {
		return address;
	}

	public void setAddress(Label address) {
		this.address = address;
	}

	public Label getlItems() {
		return lItems;
	}

	public void setlItems(Label lItems) {
		this.lItems = lItems;
	}

	public Label getlQty() {
		return lQty;
	}

	public void setlQty(Label lQty) {
		this.lQty = lQty;
	}

	public Label getlPrice() {
		return lPrice;
	}

	public void setlPrice(Label lPrice) {
		this.lPrice = lPrice;
	}

	public Label getlTotal() {
		return lTotal;
	}

	public void setlTotal(Label lTotal) {
		this.lTotal = lTotal;
	}

	public Label getlSubtotal() {
		return lSubtotal;
	}

	public void setlSubtotal(Label lSubtotal) {
		this.lSubtotal = lSubtotal;
	}

	public Label getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Label subtotal) {
		this.subtotal = subtotal;
	}

	public Label getlDiscount() {
		return lDiscount;
	}

	public void setlDiscount(Label lDiscount) {
		this.lDiscount = lDiscount;
	}

	public Label getDiscount() {
		return discount;
	}

	public void setDiscount(Label discount) {
		this.discount = discount;
	}

	public Label getlAllTotal() {
		return lAllTotal;
	}

	public void setlAllTotal(Label lAllTotal) {
		this.lAllTotal = lAllTotal;
	}

	public Label getAllTotal() {
		return allTotal;
	}

	public void setAllTotal(Label allTotal) {
		this.allTotal = allTotal;
	}

	public GridPane getHeadLeftGP() {
		return headLeftGP;
	}

	public void setHeadLeftGP(GridPane headLeftGP) {
		this.headLeftGP = headLeftGP;
	}

	public GridPane getHeadRightGP() {
		return headRightGP;
	}

	public void setHeadRightGP(GridPane headRightGP) {
		this.headRightGP = headRightGP;
	}

	public GridPane getBodyGP() {
		return bodyGP;
	}

	public void setBodyGP(GridPane bodyGP) {
		this.bodyGP = bodyGP;
	}

	public GridPane getFooterGP() {
		return footerGP;
	}

	public void setFooterGP(GridPane footerGP) {
		this.footerGP = footerGP;
	}

	public HBox getHeadHB() {
		return headHB;
	}

	public void setHeadHB(HBox headHB) {
		this.headHB = headHB;
	}

	public FlowPane getFooterFP() {
		return footerFP;
	}

	public void setFooterFP(FlowPane footerFP) {
		this.footerFP = footerFP;
	}

	public void setReport(BorderPane report) {
		this.report = report;
	}
	
	

}
