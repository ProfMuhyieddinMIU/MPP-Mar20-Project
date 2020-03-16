package business.controllers.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.LoginException;
import business.controllers.interfaces.ControllerInterface;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static User currentLoggedInUser = null;

	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();

		checkUser(map, id, password);
	}

	private void checkUser(HashMap<String, User> map, String id, String password) throws LoginException {
		if (!map.containsKey(id))
			throw new LoginException("ID " + id + " not found");
		String passwordFound = map.get(id).getPassword();
		if (!passwordFound.equals(password))
			throw new LoginException("Password incorrect");
		currentLoggedInUser = map.get(id);
	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}
}
