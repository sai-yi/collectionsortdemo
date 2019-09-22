package zero.saiyi.collectionsortdemo;

public class StockItem implements Comparable<StockItem> {
	private String name;
	private double price;
	private int quantityInStock;
	private int reserve=0;

	public StockItem(String name, double price) {
		this.name = name;
		this.price = price;
		this.quantityInStock = 0;
	}

	public StockItem(String name, double price, int quantityInStock) {
		this.name = name;
		this.price = price;
		this.quantityInStock = quantityInStock;
	}

	public int getquantityInStock() {
		return this.quantityInStock - reserve;
	}
	

	public String getName() {
		return this.name;
	}

	public void setPrice(double newPrice) {
		if (newPrice >= 0.0) {
			this.price = newPrice;
		}
	}

	public int reserveStock(int quantity) {
		if(quantity <= getquantityInStock()) {
			this.reserve += quantity;
			return quantity;
		}
		return 0;
	}
	public int unReserveStock(int quantity) {
		if(quantity <= this.reserve) {
			this.reserve -= quantity;
			return quantity;
		}
		return 0;
	}
	public int finaliseStock(int quantity) {
		if( quantity <= this.reserve) {
			this.quantityInStock -= quantity;
			this.reserve -= quantity;
			return quantity;
		}
		return 0;
	}
	
	public double getPrice() {
		return this.price;
	}

	public boolean adjustquantityInStock(int Quantity) {
		int newquantityInStock = this.quantityInStock + Quantity;
		if (newquantityInStock >= 0) {
			this.quantityInStock = newquantityInStock;
			return true;
		}
		return false;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj != this || (obj.getClass()) != this.getClass()) {
			return false;
		}
		if (obj instanceof StockItem) {
			StockItem other = (StockItem) obj;
			return this.getName().equals(other.getName());
		}
		return false;
	}

	public int hashCode() {
		return this.name.hashCode() + 54;
	}
	public String toString() {
		return "{"+this.name+" : "+this.price+" : "+this.getquantityInStock()+" } ";
	}
	public int compareTo(StockItem otherItem) {
		if (otherItem == this) {
			return 0;
		}
		if (otherItem != null) {
			return this.name.compareTo(otherItem.getName());
		}
		throw new NullPointerException("otherItem is null");
	}
}
