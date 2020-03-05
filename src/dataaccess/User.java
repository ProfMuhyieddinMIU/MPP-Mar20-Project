package dataaccess;

import java.io.Serializable;

final public class User implements Serializable 
{
	private static final long serialVersionUID = 5147265048973262104L;
	private String id;
	private String password;
	private Auth authorization;
	
	/**
	 * 
	 * @param id
	 * @param pass
	 * @param auth
	 */
	User(String id, String pass, Auth  auth) 
	{
		this.id = id;
		this.password = pass;
		this.authorization = auth;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getId() 
	{
		return id;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPassword() 
	{
		return password;
	}
	
	/**
	 * 
	 * @return
	 */
	public Auth getAuthorization() 
	{
		return authorization;
	}
	
	@Override
	public String toString() 
	{
		return "[" + id + ":" + password + ", " + authorization.toString() + "]";
	}
	
}
