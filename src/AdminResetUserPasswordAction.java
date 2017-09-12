package buyNsell.AdminLogin;

import buyNsell.BusinessObjects.*;
import buyNsell.DatabaseConnection.*;

import org.apache.struts.action.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AdminResetUserPasswordAction extends Action
{
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response)
								throws Exception
	{
		Users u=(Users)request.getSession().getAttribute("selecteduser");
		String temp=null;
		if(u != null)
		{
			String q="update users set Password='' where UserID like '" + u.getUserid() +"'";
			JdbcUtil.updtQuery(q);
			temp="reset success";
		}
		else
			temp="reset failure";
		request.getSession().setAttribute("message", temp);
		return (mapping.findForward ("success"));
	}
}