package business;

import java.io.Serializable;

final public class Author extends Person implements Serializable 
{
	private String bio;
	private static final long serialVersionUID = 7508481940058530471L;
	
	public String getBio() 
	{
		//
		return bio;
	}
	
	/**
	 *  Update the Author's Biography
	 * @param b The new updated Biography
	 */
	public void setBio(String b)
	{
		bio = b;
	}
	
	/**
	 * 
	 * @param f First Name
	 * @param l Last Name
	 * @param t Telephone Number
	 * @param a Address
	 * @param bio Biography
	 * @param em Email
	 */
	public Author(String f, String l, String t, Address a, String bio, String em) 
	{
		super(f, l, t, a, em);
		this.bio = bio;
	}
}
