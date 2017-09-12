package buyNsell.AccountOperations.SellerOperations.CatalogPosting;

import buyNsell.Utils.AuctionScheduler.*;
import buyNsell.DatabaseConnection.*;
import buyNsell.BusinessObjects.*;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.*;

public class CatalogPostAction extends Action
{
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response)
								throws Exception
	{
		CatalogPostForm catalog=(CatalogPostForm) form;
		
		if(catalog.validator())
		{
			catalog.errorExists=false;
			ResponseMessage m=new ResponseMessage();
			m.setType("Failure");
			m.setHeading("Catalog Post Failure");
			m.setContent("Either of the following errors might have Occured : <br><li>User id is Required !<br><li>Catalog id is Required !<br><li>A base price or starting price for the auction is required and has to be a digit !<br><li>Bid Increment is a must, it has to be Entered as digits !<br><li>Auction id cannot be omitted !<br><li>Auction starting date has to be entered !<br><li>Auction ending date also has to be entered !<br><li>Product id should be entered !<br><li>Product Name is Mandatory !<br><li>Product Description is required ! <br><li>Product Category is missing !<br><li>Product Quantity is missing !<br><li>Product Weight is missing !");
			m.setFooting("Sorry ! Do Try Once Again !");
			request.getSession().setAttribute("response", m);
			return (mapping.findForward ("failure"));
		}
		else
		{
			String aID		=	catalog.getAuctionid();
			String cID		=	catalog.getCatalogid();
			String aStDate	=	catalog.getAuctionstartingdate() +
								" " +catalog.getShrs()+":"+catalog.getSmins()+":"+"00";
			String aEnDate	=	catalog.getAuctionendingdate() +
								" " +catalog.getEhrs()+":"+catalog.getEmins()+":"+"00";
			String uID		= 	catalog.getUserid();
			String stPrc	=	catalog.getStartingprice();
			String bidIncr	=	catalog.getBidincrement();
			String id		=	catalog.getId();
			String name		=	catalog.getName();
			String desc		=	catalog.getDesc();
			String cat		=	catalog.getCat();
			String qty		=	catalog.getQty();
			String wt		=	catalog.getWt();
			catalog.reset(mapping, request);
			
			String query="insert into auction values('"+aID+"','"+cID+"','"+aStDate+"','"+aEnDate+"')";
			JdbcUtil.updtQuery(query);
			
			query="insert into catalog values('"+cID+"','"+uID+"','"+stPrc+"','"+bidIncr+"')";
			JdbcUtil.updtQuery(query);
			
			query="insert into product values('"+id+"','"+cID+"','"+name+"','"+desc+"','"+cat+"','"	+qty+"','"+wt+"')";
			JdbcUtil.updtQuery(query);
			
			Auction auction=new Auction();
			auction.setAuctionid(aID);			auction.setCatalogid(cID);
			auction.setStartingdate(aStDate);	auction.setEndingdate(aEnDate);
			request.getSession().setAttribute("auction", auction);
			
			Catalog catalog1=new Catalog();
			catalog1.setCatalogid(cID);			catalog1.setUserid(uID);
			catalog1.setStartingprice(stPrc);	catalog1.setBidincrement(bidIncr);
			request.getSession().setAttribute("catalog", catalog1);
			
			AutoScheduler scheduler=new AutoScheduler();
			scheduler.scheduleCurrentAuction(auction);
			scheduler.unscheduleCurrentAuction(auction);						
			return (mapping.findForward ("success"));			
		}
	}
}