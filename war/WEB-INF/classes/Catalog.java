package buyNsell.BusinessObjects;

import java.util.*;

public class Catalog
{
	protected String catalogid;
	protected String userid;
	protected String startingprice;
	protected String bidincrement;
		
	public Catalog(){}
	
	public Catalog (ArrayList raw)
	{
		this.catalogid		=(String)raw.get(0);
		this.userid			=(String)raw.get(1);
		this.startingprice	=(String)raw.get(2);
		this.bidincrement	=(String)raw.get(3);
	}
	
	public String getCatalogid()
	{
		return this.catalogid;
	}
	public void setCatalogid(String catalogid)
	{
		this.catalogid=catalogid;
	}
	
	public String getUserid()
	{
		return this.userid;
	}
	public void setUserid(String userid)
	{
		this.userid=userid;
	}
	
	
	public String getStartingprice()
	{
		return this.startingprice;
	}
	public void setStartingprice(String startingprice)
	{
		this.startingprice=startingprice;
	}
	
	public String getBidincrement()
	{
		return this.bidincrement;
	}
	public void setBidincrement(String bidincrement)
	{
		this.bidincrement=bidincrement;
	}
}