package buyNsell.UserLogin;

import buyNsell.BusinessObjects.*;

import org.apache.struts.action.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class UserLogoutAction extends Action
{
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response)
								throws Exception
	{
		ResponseMessage m=new ResponseMessage();
		if(request.getSession().getAttribute("user")!=null)
		{
			request.getSession().removeAttribute("user");
			m.setType("Success");
			m.setHeading("Logout Successful");
			m.setContent("You have succesfully logged out.");
			m.setFooting("Thank You !");
		}
		else
		{
			m.setType("Failure");
			m.setHeading("Logout Failure - Session Invalid");
			m.setContent("This is not a place to play around !");
			m.setFooting("Session Invalid !");
		}
		request.getSession().setAttribute("response", m);
		return (mapping.findForward ("response"));
	}
}






