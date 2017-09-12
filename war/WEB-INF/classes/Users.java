package buyNsell.BusinessObjects;

import java.util.*;

public class Users  
{
	protected String userid;
	protected String firstname;
	protected String lastname;
	protected String companyname;
	protected String emailid;
	protected String password;
	protected String hintques;
	protected String hintans;
	protected String dateofbirth;
	protected String address;	
	protected String shippingaddress;
	protected String country;
	protected String city;
	protected String state;
	protected String pin;
	protected String phone;
	protected String fax;
	
	public Users(){}
	
	public Users (ArrayList raw)
	{
		this.userid			=(String)raw.get(0);
		this.firstname		=(String)raw.get(1);
		this.lastname		=(String)raw.get(2);
		this.companyname	=(String)raw.get(3);
		this.emailid		=(String)raw.get(4);
		this.password		=(String)raw.get(5);
		this.hintques		=(String)raw.get(6);
		this.hintans		=(String)raw.get(7);
		this.dateofbirth	=(String)raw.get(8);
		this.address		=(String)raw.get(9);
		this.shippingaddress=(String)raw.get(10);
		this.country		=(String)raw.get(11);
		this.city			=(String)raw.get(12);
		this.state			=(String)raw.get(13);
		this.pin			=(String)raw.get(14);
		this.phone			=(String)raw.get(15);
		this.fax			=(String)raw.get(16);
	}
	
	public String getUserid()
	{
		return this.userid;
	}
	public void setUserid(String userid)
	{
		this.userid=userid;
	}
	
	
	public String getFirstname()
	{	
		return this.firstname;	
	}
	public void setFirstname(String firstname)
	{
		this.firstname=firstname;
	}
	
	public String getLastname()
	{
		return this.lastname;
	}
	public void setLastname(String lastname)
	{
		this.lastname=lastname;
	}
	
	public String getCompanyname()
	{
		return this.companyname;
	}
	public void setCompanyname(String companyname)
	{
		this.companyname=companyname;
	}
	
	public String getEmailid()
	{
		return this.emailid;
	}
	public void setEmailid(String emailid)
	{
		this.emailid=emailid;
	}
	
	
	public String getPassword()
	{
		return this.password;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}
	
	public String getHintques()
	{
		return this.hintques;
	}
	public void setHintques(String hintques)
	{
		this.hintques=hintques;
	}
	
	public String getHintans()
	{
		return this.hintans;
	}
	public void setHintans(String hintans)
	{
		this.hintans=hintans;
	}	
	
	public String getDateofbirth()
	{
		return this.dateofbirth;
	}
	public void setDateofbirth(String dateofbirth)
	{
		this.dateofbirth=dateofbirth;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	public void setAddress(String address)
	{
		this.address=address;
	}
	
	public String getShippingaddress()
	{
		return this.shippingaddress;
	}
	public void setShippingaddress(String shippingaddress)
	{
		this.shippingaddress=shippingaddress;
	}
	
	public String getCountry()
	{
		return this.country;
	}
	public void setCountry(String country)
	{
		this.country=country;
	}
	
	public String getCity()
	{
		return this.city;
	}
	public void setCity(String city)
	{
		this.city=city;
	}
	
	public String getState()
	{
		return this.state;
	}
	public void setState(String state)
	{
		this.state=state;
	}
	
	public String getPin()
	{
		return this.pin;
	}
	public void setPin(String pin)
	{
		this.pin=pin;
	}
	
	public String getPhone()
	{
		return this.phone;
	}
	public void setPhone(String phone)
	{
		this.phone=phone;
	}
	
	public String getFax()
	{
		return this.fax;
	}
	public void setFax(String fax)
	{
		this.fax=fax;
	}
}