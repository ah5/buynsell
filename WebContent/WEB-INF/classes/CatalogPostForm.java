package buyNsell.AccountOperations.SellerOperations.CatalogPosting;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.lang.*;

public class CatalogPostForm extends ActionForm
{
	String userid;
	String catalogid;
	String startingprice;
	String bidincrement;
	String auctionid;
	String auctionstartingdate;
	String auctionendingdate;
	String shrs;
	String smins;
	String emins;
	String ehrs;
	String id;
	String name;
	String desc;
	String cat;
	String qty;
	String wt;
	
	public static boolean errorExists=false;
	
	public String getShrs()
	{	
		return this.shrs;	
	}
	public void setShrs(String shrs)
	{
		this.shrs=shrs;
	}
	
	public String getSmins()
	{	
		return this.smins;	
	}
	public void setSmins(String smins)
	{
		this.smins=smins;
	}
	
	public String getEhrs()
	{	
		return this.shrs;	
	}
	public void setEhrs(String shrs)
	{
		this.shrs=shrs;
	}
	
	public String getEmins()
	{	
		return this.smins;	
	}
	public void setEmins(String smins)
	{
		this.smins=smins;
	}
	
	public String getId()
	{	
		return this.id;	
	}
	public void setId(String id)
	{
		this.id=id;
	}
	
	public String getName()
	{	
		return this.name;	
	}
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getDesc()
	{	
		return this.desc;	
	}
	public void setDesc(String desc)
	{
		this.desc=desc;
	}
		
	public String getCat()
	{	
		return this.cat;	
	}
	public void setCat(String cat)
	{
		this.cat=cat;
	}
	
	public String getQty()
	{	
		return this.qty;	
	}
	public void setQty(String qty)
	{
		this.qty=qty;
	}
	
	public String getWt()
	{	
		return this.wt;	
	}
	public void setWt(String wt)
	{
		this.wt=wt;
	}
	
	public String getUserid()
	{	
		return this.userid;	
	}
	public void setUserid(String userid)
	{
		this.userid=userid;
	}
	
	public String getCatalogid()
	{	
		return this.catalogid;	
	}
	public void setCatalogid(String catalogid)
	{
		this.catalogid=catalogid;
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
	
	public String getAuctionid()
	{	
		return this.auctionid;	
	}
	public void setAuctionid(String auctionid)
	{
		this.auctionid=auctionid;
	}
	
	public String getAuctionstartingdate()
	{	
		return this.auctionstartingdate;	
	}
	public void setAuctionstartingdate(String auctionstartingdate)
	{
		this.auctionstartingdate=auctionstartingdate;
	}
	
	public String getAuctionendingdate()
	{	
		return this.auctionendingdate;	
	}
	public void setAuctionendingdate(String auctionendingdate)
	{
		this.auctionendingdate=auctionendingdate;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
		this.userid=null;
		this.catalogid=null;
		this.startingprice=null;
		this.bidincrement=null;
		this.auctionid=null;
		this.auctionstartingdate=null;
		this.auctionendingdate=null;
		this.id=null;
		this.name=null;
		this.desc=null;
		this.cat=null;
		this.qty=null;
		this.wt=null;
	}
	
	public boolean validator() 
	{
		char [] chars;
		
		if ((userid == null) || (userid.length() < 1))
			errorExists=true;
		if ((catalogid == null) || (catalogid.length() < 1))
			errorExists=true;
		
		if ((startingprice == null) || (startingprice.length() < 1))
			errorExists=true;
		chars=startingprice.toCharArray();
		for(int i=0; i<chars.length; i++)
			if(! Character.isDigit(chars[i]))
				errorExists=true;
		
		if ((bidincrement == null) || (bidincrement.length() < 1))
			errorExists=true;
		chars=bidincrement.toCharArray();
		for(int i=0; i<chars.length; i++)
			if(! Character.isDigit(chars[i]))
				errorExists=true;	
			
		if ((auctionstartingdate == null) || (auctionstartingdate.length() < 1))
			errorExists=true;
		if ((auctionendingdate == null) || (auctionendingdate.length() < 1))
			errorExists=true;
		
		if ((auctionid == null) || (auctionid.length() < 1))
			errorExists=true;
		if ((id == null) || (id.length() < 1))
			errorExists=true;
		if ((name == null) || (name.length() < 1))
			errorExists=true;
		if ((desc == null) || (desc.length() < 1))
			errorExists=true;
		if ((cat == null) || (cat.length() < 1))
			errorExists=true;
		if ((qty == null) || (qty.length() < 1))
			errorExists=true;
		chars=qty.toCharArray();
		for(int i=0; i<chars.length; i++)
			if(! Character.isDigit(chars[i]))
				errorExists=true;		
	
		if ((wt == null) || (wt.length() < 1))
			errorExists=true;
		chars=wt.toCharArray();
		for(int i=0; i<chars.length; i++)
			if(! Character.isDigit(chars[i]))
				errorExists=true;	
		
		return errorExists;
	}
}