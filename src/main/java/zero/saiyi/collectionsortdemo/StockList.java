package zero.saiyi.collectionsortdemo;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collections;
//import java.util.HashMap;

public class StockList {
	private final Map<String, StockItem> stockLists;

	public StockList() {
		stockLists = new LinkedHashMap<>();
	}

	public int addItem(StockItem item) {
		if (item != null) {
			StockItem inStock = stockLists.getOrDefault(item.getName(), item);
			if (inStock != item) {
				item.adjustquantityInStock(inStock.getquantityInStock());				
			}
			stockLists.put(item.getName(), item);
			return item.getquantityInStock();
		}
		return 0;
	}

	public int sellStock(StockItem sellItem, int quantity) {
		StockItem inStock = stockLists.getOrDefault(sellItem.getName(), null);
	//	System.out.println(inStock);
		if(inStock != null && quantity >0) {
			return inStock.finaliseStock(quantity);
			//return quantity;
		}
		
//		StockItem inStock = stockLists.getOrDefault(sellItem.getName(), null);
//
//		if (inStock != null && (inStock.getquantityInStock() >= quantity) && quantity > 0) {
//			inStock.adjustquantityInStock(-quantity);
//			
//			return quantity;
//		}

		return 0;
	}
	public int reserveStock(StockItem item,int quantity) {
		StockItem inStock=stockLists.get(item.getName());
		if((inStock != null) && quantity > 0) {
			
			return inStock.reserveStock(quantity);
		}
		return 0;
	}
	
	public int unReserveStock(StockItem item, int quantity){
		StockItem inStock = stockLists.get(item.getName());
		if((inStock != null) && quantity > 0) {
			return inStock.unReserveStock(quantity);
		}
		
		return 0;
	}
	
	public StockItem get(String key) {
		return stockLists.get(key);
	}

	public Map<String, StockItem> getStockLists() {
		return Collections.unmodifiableMap(stockLists);
	}

	public String toString() {
		
		String str="\n In Stock \n";
		//double totalAmount=0.0;
		for(Map.Entry<String,StockItem> item : stockLists.entrySet()) {
			StockItem itemInStock = item.getValue();
			//double amounts= itemInStock.getPrice() * itemInStock.getquantityInStock();
			
			str += itemInStock.getName() + " There are "+ itemInStock.getquantityInStock()+" in the Stock";
 			//totalAmount += amounts;
			str+=" : reserve "+item.getValue().getReserve()+" ";
 			str+= " and value of items : "+String.format("%.2f", itemInStock.getPrice() * itemInStock.getquantityInStock())+"\n";
		}
		
		return str;
	}
}
