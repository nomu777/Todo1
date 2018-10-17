package model;

public class TodoLoginLogic {
	public boolean execute(TodoUser user) {
		if(user.getPass().equals("pass")) {
			return true;
		}
		return false;
	}
}
