package business.controllers.interfaces;

import business.LoginException;

public interface ControllerInterface
{
	public void login(String id, String password) throws LoginException;

	
}
