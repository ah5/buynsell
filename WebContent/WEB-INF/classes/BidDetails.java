package buyNsell.BusinessObjects;

import java.util.*;

public class BidDetails
{
	protected String bidid;
	protected String productid;
	protected String bidqty;
		
	public BidDetails(){}
	
	public BidDetails (ArrayList raw)
	{
		this.bidid		=(String)raw.get(0);
		this.productid	=(String)raw.get(1);
		this.bidqty		=(String)raw.get(2);
	}
	
	public String getBidid()
	{
		return this.bidid;
	}
	public void setBidid(String bidid)
	{
		this.bidid=bidid;
	}
	
	public String getProductid()
	{
		return this.productid;
	}
	public void setProductid(String productid)
	{
		this.productid=productid;
	}
	
	public String getBidqty()
	{
		return this.bidqty;
	}
	public void setBidqty(String bidqty)
	{
		this.bidqty=bidqty;
	}	
	
}