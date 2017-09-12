package buyNsell.AccountOperations;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class CreditCardEditForm extends ActionForm
{
	String userid;
	String ccnumber;
	String cctype;
	String expmonth;
	String expyear;
	
	public String getUserid()
	{
		return this.userid;
	}
	public void setUserid(String userid)
	{
		this.userid=userid;
	}
	
	
	public String getCcnumber()
	{
		return this.ccnumber;
	}
	public void setCcnumber(String ccnumber)
	{
		this.ccnumber=ccnumber;
	}
	
	public String getCctype()
	{
		return this.cctype;
	}
	public void setCctype(String cctype)
	{
		this.cctype=cctype;
	}
	
	public String getExpmonth()
	{
		return this.expmonth;
	}
	public void setExpmonth(String expmonth)
	{
		this.expmonth=expmonth;
	}
	
	public String getExpyear()
	{
		return this.expyear;
	}
	public void setExpyear(String expyear)
	{
		this.expyear=expyear;
	}
	
	public void reset()
	{
		setUserid(null);
		setCcnumber(null);
		setCctype(null);
		setExpmonth(null);
		setExpyear(null);
	}

	public boolean validator()
	{
		boolean errorExists=false;
		if ((ccnumber == null) || (ccnumber.length() < 1))
			errorExists=true;
		if ((cctype == null) || (cctype.length() < 1))
			errorExists=true;
		if ((expmonth == null) || (expmonth.length() < 1))
			errorExists=true;
		if ((expyear == null) || (expyear.length() < 1))
			errorExists=true;
		return errorExists;
	}
}