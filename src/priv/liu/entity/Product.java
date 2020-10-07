package priv.liu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String _name;
	@Column(name = "price")
	private int _price;
	@Column(name = "img")
	private String _img;
	
	public Product() {}
	
	public Product(String name, int price) {
		super();
		setName(name);
		setPrice(price);
	}
	
	public Product(String name, int price, String img) {
		super();
		setName(name);
		setPrice(price);
		setImg(img);
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
	
	public String getImg() {
		return _img;
	}
	
	public void setImg(String img) {
		this._img = img;
	}
}
