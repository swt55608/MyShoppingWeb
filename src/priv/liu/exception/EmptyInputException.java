package priv.liu.exception;

public class EmptyInputException extends Exception {
	public EmptyInputException() {
		super("Input Should Not Be Empty!");
	}
}
