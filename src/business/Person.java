package business;

import java.io.Serializable;

public abstract class Person implements Serializable
{
	private static final long serialVersionUID = 3665880920647848288L;
	private String firstName;
	private String lastName;
	private String telephone;
	private Address address;
	private String email;
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null) 
			return false;
		if(!(obj instanceof Person)) 
			return false;
		Person p = (Person)obj;
		return p.getFirstName().equals(firstName) && p.lastName == lastName;
	}
	/**
	 * 
	 * @param f First name
	 * @param l Last name
	 * @param t Telephone number
	 * @param a Address
	 * @param e Email
	 */
	public Person(String f, String l, String t, Address a, String e)
	{
		firstName = f;
		lastName = l;
		telephone = t;
		address = a;
		email = e;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getTelephone()
	{
		return telephone;
	}
	
	public Address getAddress()
	{
		return address;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String e)
	{
		email = e;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	
	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}
	
	public void setAddress(Address address) 
	{
		this.address = address;
	}
}
