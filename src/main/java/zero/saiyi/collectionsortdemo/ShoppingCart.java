package zero.saiyi.collectionsortdemo;

import java.util.Map;
import java.util.Collections;
import java.util.HashMap;

public class ShoppingCart {
	private String name;
	private final Map<StockItem, Integer> lists;

	public ShoppingCart(String name) {
		this.name = name;
		this.lists = new HashMap<>();
	}

	public String getName() {
		return this.name;
	}

	public int addToCart(StockItem item, int quantity) {
		if (item != null && quantity > 0) {
			int inStock = lists.getOrDefault(item, 0);

			lists.put(item, inStock + quantity);
			return quantity;
		}
		return 0;
	}

	public int removeItem(StockItem item, int quantity) {
		if(item != null & quantity > 0) {
			
			int quantityOfItemInCart = lists.getOrDefault(item, 0);
			int newQuantity = quantityOfItemInCart - quantity;
			
			if(newQuantity > 0) {
				lists.put(item, newQuantity);
				return quantity;
			}else if( newQuantity == 0) {
				lists.remove(item);
				return 0;
			}
			
		}	
		
		return 0;
	}
	
	public void clearCart() {
		this.lists.clear();
	}
	
	public Map<StockItem, Integer> getLists() {
		return Collections.unmodifiableMap(lists);
	}

	public String toString() {
		String str = this.name + "'s cart have " + lists.values().size() +(lists.size() == 1 ? "item" : "items") + " \n";
		double amount = 0.0;
		for (StockItem key : lists.keySet()) {
			str += " " + key.getName() + " : " + lists.get(key) + " items (x1) price : " + key.getPrice() + " total = "
					+ (lists.get(key) * key.getPrice()) + "\n";

			amount += key.getPrice() * lists.get(key);
		}

		return str + "  and total amount  " + String.format("%.2f", amount);
	}
}
