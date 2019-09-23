package zero.saiyi.collectionsortdemo;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
	private static StockList stockList = new StockList();

	public static void main(String[] args) {

		StockItem temp = new StockItem("Cup", 13.25, 50);
		stockList.addItem(temp);

		temp = new StockItem("Book", 3.50, 100);
		stockList.addItem(temp);

		temp = new StockItem("Beer", 1.55, 150);
		stockList.addItem(temp);

		temp = new StockItem("Juice", 3.25, 40);
		stockList.addItem(temp);

		temp = new StockItem("Bread", 2.25, 200);
		stockList.addItem(temp);

		System.out.println(stockList);
		ShoppingCart tom = new ShoppingCart("Tom");
		shopping(tom, "Book", 10);
		System.out.println(tom);
		shopping(tom, "Beer", 10);
		System.out.println(tom);
		shopping(tom, "Juice", 40);
		System.out.println(tom);
		unReserveItem(tom, "Beer", 5);
		System.out.println(" -- Before CheckOut Items in Stock ----");
		System.out.println(stockList);
		System.out.println("-----------------------------------------");
		checkOut(tom);
		System.out.println("Tom's Cart After Checkout !!!!!!! must be zero");
		System.out.println(tom);

		System.out.println("---------------------------------");
		System.out.println(" --- After Checkout Item's in Stock --------");
		System.out.println(stockList);
		
		ShoppingCart nick =new ShoppingCart("Nick");
		if(shopping(nick, "Juice", 10) ==0) {
			System.out.println("Sorry Juices have gone choose another");
		}
		System.out.println("reduce Beer -65 "+stockList.get("Beer").adjustquantityInStock(-65));
		shopping(nick,"Beer",80);
		shopping(nick,"Ball Point Pen",30);
		System.out.println("This one will not be in the cart");
		shopping(nick,"Beer",4);
		System.out.println(nick);

		shopping(nick,"Book",50);
		System.out.println("Before ========== checkout");
		System.out.println(stockList);
		checkOut(nick);
		System.out.println("After ========== checkOut");
		System.out.println(stockList);
	}
	
	public static void checkOut(ShoppingCart cart) {
		for(Map.Entry<StockItem, Integer> item : cart.getLists().entrySet()) {
			stockList.sellStock(item.getKey(), item.getValue());
		}
		cart.clearCart();
	}

	public static int shopping(ShoppingCart cart, String item, int quantity) {
		
		StockItem inStock = stockList.get(item);
		if(inStock == null) {
			System.out.println("Sorry we don't sell "+ item);
			return 0;
		}
		if(stockList.reserveStock(inStock,quantity) != 0) {
			return cart.addToCart(inStock, quantity);
		}
		
		return 0;

	}
	public static int unReserveItem(ShoppingCart cart,String item,int quantity) {
		StockItem inStock = stockList.get(item);
		if(inStock == null) {
			return 0;
		}	
		if(cart.removeItem(inStock,quantity) != 0) {
			return stockList.unReserveStock(inStock, quantity);
		}
	   return 0;
	}
	
	
}
