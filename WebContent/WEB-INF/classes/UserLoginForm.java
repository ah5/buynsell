package buyNsell.UserLogin;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class UserLoginForm extends ActionForm
{
	String userid=null;
	String pass=null;
	static boolean errorExists=false;
	
	public String getUserid()
	{	
		return this.userid;	
	}
	public void setUserid(String userid)
	{
		this.userid=userid;
	}
	
	public String getPass()
	{
		return this.pass;
	}
	public void setPass(String pass)
	{
		this.pass=pass;
	}
	
	public void reset()
	{
		errorExists =false;
		setUserid(null);
		setPass(null);
	}
	
	public boolean validator() 
	{
		if ((userid == null) || (userid.length() < 1))
			errorExists=true;
		if ((pass == null) || (pass.length() < 1))
			errorExists=true;
		return errorExists;
	}
}