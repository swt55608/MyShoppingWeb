package priv.liu.entity;

public class Product {
	private String _name;
	private int _price;
	
	public Product(String name, int price) {
		super();
		setName(name);
		setPrice(price);
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}
	
	public int getPrice() {
		return _price;
	}
	
	public void setPrice(int price) {
		this._price = price;
	}
}
