package buyNsell.AccountOperations;

import buyNsell.BusinessObjects.*;
import buyNsell.DatabaseConnection.*;

import org.apache.struts.action.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ChangePasswordAction extends Action
{
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response)
								throws Exception
	{
		ChangePasswordForm p=(ChangePasswordForm) form;
		String temp;
		
		if(p.validator())
		{
			p.reset();
			temp="failure";
		}
		else
		{
			Users u=(Users)request.getSession().getAttribute("user");
			if(u.getPassword().equals(p.getOld()))
			{
				String q="update users set Password='"+ p.getNew1() +"' where UserID like'"+ u.getUserid() +"'";
				JdbcUtil.updtQuery(q);
				temp="success";
			}
			else
				temp="failure";
			p.reset();
		}
		if(temp.equals("failure"))
		{
			ResponseMessage m=new ResponseMessage();
			m.setType("Failure");
			m.setHeading("Password Change Failure - Invalid Entries");
			m.setContent("<li>Old Password seems to be invalid !<br><li>The set of New passwords don't match !<br>");
			m.setFooting("Sorry ! Do Try Once Again !");
			request.getSession().setAttribute("response", m);
		}
		else
		{
			ResponseMessage m=new ResponseMessage();
			m.setType("Success");
			m.setHeading("Password Change Success");
			m.setContent("Your Password has been changed Successfully !");
			m.setFooting("Thank You !");
			request.getSession().setAttribute("response", m);
		}
		return (mapping.findForward ("response"));
	}
}
