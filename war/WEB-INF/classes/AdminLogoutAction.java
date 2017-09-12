package buyNsell.AdminLogin;

import buyNsell.BusinessObjects.*;

import org.apache.struts.action.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AdminLogoutAction extends Action
{
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response)
								throws Exception
	{
		ResponseMessage m=new ResponseMessage();
		if(request.getSession().getAttribute("admin")!=null)
		{
			request.getSession().removeAttribute("admin");
			m.setType("Success");
			m.setHeading("Admin Logout Successful");
			m.setContent("All admin rights taken off <br> Normal State Loaded");
			m.setFooting("Thank You!");
		}
		else
		{
			m.setType("Failure");
			m.setHeading("Admin Logout Failure - Session Invalid");
			m.setContent("This is not a place to play around !");
			m.setFooting("Session Invalid !");
		}
		request.getSession().setAttribute("response", m);
		return (mapping.findForward ("response"));
	}
}






