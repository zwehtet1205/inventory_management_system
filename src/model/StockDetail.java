package model;

public class StockDetail {

    private int warehouse_id;
    private int item_id;
    private int openingQty;
    private int purchaseQty;
    private int purchaseReturnQty;
    private int saleQty;
    private int saleReturnQty;
    private int transferIn;
    private int transferOut;
    private int closingQty;
    private String warehouseName,itemName,itemCode;

    public StockDetail(int warehouse_id, int item_id, int openingQty, int purchaseQty, int purchaseReturnQty,
                       int saleQty, int saleReturnQty, int transferIn, int transferOut) {
        this.warehouse_id = warehouse_id;
        this.item_id = item_id;
        this.openingQty = openingQty;
        this.purchaseQty = purchaseQty;
        this.purchaseReturnQty = purchaseReturnQty;
        this.saleQty = saleQty;
        this.saleReturnQty = saleReturnQty;
        this.transferIn = transferIn;
        this.transferOut = transferOut;
        this.closingQty = calculateClosingQty();
    }

    private int calculateClosingQty() {
        return openingQty + purchaseQty - purchaseReturnQty - saleQty + saleReturnQty + transferIn - transferOut;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public int getOpeningQty() {
        return openingQty;
    }

    public int getPurchaseQty() {
        return purchaseQty;
    }

    public int getPurchaseReturnQty() {
        return purchaseReturnQty;
    }

    public int getSaleQty() {
        return saleQty;
    }

    public int getSaleReturnQty() {
        return saleReturnQty;
    }

    public int getTransferIn() {
        return transferIn;
    }

    public int getTransferOut() {
        return transferOut;
    }

    public int getClosingQty() {
        return closingQty;
    }

    public String getWarehouseName() {
		return warehouseName;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    
    
}
