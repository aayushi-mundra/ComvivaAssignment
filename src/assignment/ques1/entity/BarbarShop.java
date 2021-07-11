package assignment.ques1.entity;

public class BarbarShop {

	private String shopName;
	private int capacity;
	
	public BarbarShop(String shopName, int capacity) {
		this.shopName = shopName;
		this.capacity = capacity;
	}
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}
