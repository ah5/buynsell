package buyNsell.AccountOperations.SellerOperations;

import buyNsell.BusinessObjects.*;
import buyNsell.DatabaseConnection.*;

import org.apache.struts.action.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.*;

public class SellerSelectAuctionAction extends Action
{
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response)
								throws Exception
	{
		SellerSelectAuctionForm obj=(SellerSelectAuctionForm) form;
		
		if(obj.validator())
		{
			ResponseMessage m=new ResponseMessage();
			m.setType("Failure");
			m.setHeading("Auction Status Fetching Failure");
			m.setContent("<li>You have not selected the auction <br>for which the status is required !");
			m.setFooting("Sorry ! Try Once Again !");
			request.getSession().setAttribute("response", m);
			return (mapping.findForward ("failure"));
		}
		else
		{
			Catalog catalog1=new Catalog();
			catalog1.setCatalogid(obj.getAuctionSelected());
			request.getSession().setAttribute("sellerselectedcatalog", catalog1);
			return (mapping.findForward ("success"));
		}
	}
}