package priv.liu.exception;

public class UserExistException extends Exception {
	public UserExistException() {
		super("User Already Exists...Please use other username!");
	}
}
