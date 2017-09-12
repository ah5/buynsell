package buyNsell.BusinessObjects;

import java.util.*;

public class Bid
{
	protected String bidid;
	protected String auctionid;
	protected String userid;
	protected String bidprice;
		
	public Bid(){}
	
	public Bid (ArrayList raw)
	{
		this.bidid		=(String)raw.get(0);
		this.auctionid	=(String)raw.get(1);
		this.userid		=(String)raw.get(2);
		this.bidprice	=(String)raw.get(3);
	}
	
	public String getBidid()
	{
		return this.bidid;
	}
	public void setBidid(String bidid)
	{
		this.bidid=bidid;
	}
	
	public String getAuctionid()
	{
		return this.auctionid;
	}
	public void setAuctionid(String auctionid)
	{
		this.auctionid=auctionid;
	}
	
	public String getUserid()
	{
		return this.userid;
	}
	public void setUserid(String userid)
	{
		this.userid=userid;
	}

	public String getBidprice()
	{
		return this.bidprice;
	}
	public void setBidprice(String bidprice)
	{
		this.bidprice=bidprice;
	}	
	
}