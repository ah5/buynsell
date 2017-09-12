package buyNsell.AccountOperations.BuyerOperations.BidPosting;

import org.apache.struts.action.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class BidForAuctionAction extends Action
{
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response)
								throws Exception
	{
		return (mapping.findForward ("success"));
	}
}