package buyNsell.BusinessObjects;

import java.util.*;

public class Admin
{
	protected String id;
	protected String pass;
	
	public Admin(){}
	
	public Admin (ArrayList raw)
	{
		this.id			=(String)raw.get(0);
		this.pass		=(String)raw.get(1);
	}
	
	public String getId()
	{
		return this.id;
	}
	public void setId(String id)
	{
		this.id=id;
	}
		
	public String getPass()
	{
		return this.pass;
	}
	public void setPass(String pass)
	{
		this.pass=pass;
	}
	
}