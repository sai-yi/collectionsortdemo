package zero.saiyi.collectionsortdemo;

/**
 * Hello world!
 *
 */
public class App {
	private static StockList stockList = new StockList();

	public static void main(String[] args) {
		// System.out.println("Hello World!");
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

//		temp =new StockItem("Bread",3.0,20);
//		stockList.addItem(temp);
//		

		System.out.println(stockList);
		ShoppingCart tom = new ShoppingCart("Tom");
		shopping(tom, stockList.get("Book"), 10);
		System.out.println(tom);
		shopping(tom, stockList.get("Beer"), 10);
		System.out.println(tom);
		shopping(tom, stockList.get("Juice"), 40);
		System.out.println(tom);
		
		System.out.println(stockList);
		
		System.out.println("test modefy item in stock");
		stockList.get("Juice").adjustquantityInStock(-40);
		
		
		stockList.get("Beer").adjustquantityInStock(-80);
		shopping(tom,stockList.get("Beer"),60);
		System.out.println(stockList);
		

	}

	public static int shopping(ShoppingCart cart, StockItem item, int quantity) {
		StockItem inStock = stockList.get(item.getName());
		if (inStock.getquantityInStock() >= quantity && quantity > 0) {

			if (stockList.sellStock(item, quantity) != 0) {
				cart.addToCart(item, quantity);
				return quantity;
			}
		}
		System.out.println("Sorry we don't sell " + item);
		return 0;

	}

}
