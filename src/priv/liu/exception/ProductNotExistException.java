package priv.liu.exception;

public class ProductNotExistException extends Exception {
	public ProductNotExistException() {
		super();
	}
	
	public ProductNotExistException(String msg) {
		super(msg);
	}
}
