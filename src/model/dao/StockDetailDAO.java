package model.dao;

import java.sql.*;
import java.util.ArrayList;

import model.entities.StockDetail;


public class StockDetailDAO extends DBHandler{

    // Method to retrieve stock details from the stored procedure
    public static ArrayList<StockDetail> getStockDetails(String categoryName, String warehouseName, Date startDate, Date endDate) {
    	ArrayList<StockDetail> stockDetails = new ArrayList<>();
        try {
			openConnection();
			String sql = "{ CALL getstockdetail_proc(?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, categoryName);
            stmt.setString(2, warehouseName);
            stmt.setDate(3, startDate);
            stmt.setDate(4, endDate);
            
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                StockDetail stockDetail = new StockDetail(
                    rs.getInt("warehouse_id"),
                    rs.getInt("item_id"),
                    rs.getInt("openingQty"),
                    rs.getInt("purchaseQty"),
                    rs.getInt("purchaseReturnQty"),
                    rs.getInt("saleQty"),
                    rs.getInt("saleReturnQty"),
                    rs.getInt("transferIn"),
                    rs.getInt("transferOut")
                );
                stockDetail.setWarehouseName(rs.getString("warehouse_name"));
                stockDetail.setItemCode(rs.getString("item_code"));
                stockDetail.setItemName(rs.getString("item_name"));
                
                stockDetails.add(stockDetail);
            }
		}catch(Exception e) 
		{
			e.printStackTrace();
		}

        return stockDetails;
    }
}
