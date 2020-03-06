package ui.dataModel;

import business.Address;
import business.LibraryMember;

public class MemberDataModel {
	
	private String memberId;
	private String firstName;
	private String lastName;
	private String telephone;
	private String address;
	private String email;
	
	public MemberDataModel(LibraryMember m) {
		this.memberId = m.getMemberId();
		this.firstName = m.getFirstName();
		this.lastName = m.getLastName();
		this.telephone = m.getTelephone();
		this.address = getAddress(m.getAddress());
		this.email = m.getEmail();
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress(Address a) {
		return a.getStreet()+", "+a.getCity() + "," + a.getState() +" " + a.getZip() ; 
	}
	

}
