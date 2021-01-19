package priv.liu.exception;

public class InvalidPasswordException extends Exception {
	public InvalidPasswordException() {
		super("Invalid Password!!!\n The length must be 6 to 15 characters long!!\n And should contain both english and number!!");
	}
}
