package buyNsell.AdminLogin;

import buyNsell.BusinessObjects.*;
import buyNsell.DatabaseConnection.*;

import org.apache.struts.action.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.*;

public class AdminSelectAuctionAction extends Action
{
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response)
								throws Exception
	{
		AdminSelectAuctionForm obj=(AdminSelectAuctionForm) form;
		Auction auction1=JdbcData.loadAuctionExplicit(obj.getAuctionSelected());
		request.getSession().setAttribute("selectedauction", auction1);
		obj.reset();
		return (mapping.findForward ("success"));
	}
}