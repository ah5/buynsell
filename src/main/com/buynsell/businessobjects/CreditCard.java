package buyNsell.BusinessObjects;

import java.util.*;

public class CreditCard  
{
	protected String ccnumber;
	protected String cctype;
	protected String ccexpiry;
		
	public CreditCard(){}
	
	public CreditCard (ArrayList raw)
	{
		this.ccnumber	=(String)raw.get(1);
		this.cctype		=(String)raw.get(2);
		this.ccexpiry	=(String)raw.get(3);
	}
	
	public String getCCnumber()
	{
		return this.ccnumber;
	}
	public void setCCnumber(String ccnumber)
	{
		this.ccnumber=ccnumber;
	}
	
	public String getCCtype()
	{
		return this.cctype;
	}
	public void setCCtype(String cctype)
	{
		this.cctype=cctype;
	}
	
	public String getCCexpiry()
	{
		return this.ccexpiry;
	}
	public void setCCexpiry(String ccexpiry)
	{
		this.ccexpiry=ccexpiry;
	}
	
}