package buyNsell.AdminLogin;

import buyNsell.BusinessObjects.*;
import buyNsell.DatabaseConnection.*;

import org.apache.struts.action.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AdminDeleteUserAction extends Action
{
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response)
								throws Exception
	{
		Users u=(Users)request.getSession().getAttribute("selecteduser", user1);
		
		String temp="DELETE";
		request.getSession().setAttribute("message", temp);
		
		return (mapping.findForward ("success"));
	}
}