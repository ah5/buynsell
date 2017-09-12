package buyNsell.BusinessObjects;

import java.util.*;

public class Auction
{
	protected String auctionid;
	protected String catalogid;
	protected String startingdate;
	protected String endingdate;
		
	public Auction(){}
	
	public Auction (ArrayList raw)
	{
		this.auctionid		=(String)raw.get(0);
		this.catalogid		=(String)raw.get(1);
		this.startingdate	=(String)raw.get(2);
		this.endingdate		=(String)raw.get(3);
	}
	
	public String getAuctionid()
	{
		return this.auctionid;
	}
	public void setAuctionid(String auctionid)
	{
		this.auctionid=auctionid;
	}
		
	public String getCatalogid()
	{
		return this.catalogid;
	}
	public void setCatalogid(String catalogid)
	{
		this.catalogid=catalogid;
	}
	
	public String getStartingdate()
	{
		return this.startingdate;
	}
	public void setStartingdate(String startingdate)
	{
		this.startingdate=startingdate;
	}
	
	public String getEndingdate()
	{
		return this.endingdate;
	}
	public void setEndingdate(String endingdate)
	{
		this.endingdate=endingdate;
	}
}