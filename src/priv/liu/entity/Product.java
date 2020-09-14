package priv.liu.entity;

public class Product {
	private String _img;
	private String _name;
	
	public Product(String img, String name) {
		super();
		setImg(img);
		setName(name);
	}

	public String getImg() {
		return _img;
	}

	public void setImg(String img) {
		this._img = img;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}
}
