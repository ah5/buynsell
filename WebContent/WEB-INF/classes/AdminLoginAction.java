package buyNsell.AdminLogin;

import buyNsell.UserLogin.*;
import buyNsell.DatabaseConnection.*;
import buyNsell.BusinessObjects.*;

import org.apache.struts.action.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AdminLoginAction extends Action
{
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response)
								throws Exception
	{
		UserLoginForm login=(UserLoginForm) form;
		String userid	=	login.getUserid();
		String pass		=	login.getPass();
		
		Admin admin=new Admin();
		admin=JdbcData.loadAdmin(userid, pass);
		String temp;
		
		if(login.validator())
		{
			login.reset();
			temp="failure";
		}
		else
		{
			login.reset();
			if(admin==null)
				temp="failure";
			else
			{
					request.getSession().setAttribute("admin", admin);
					temp="success";
			}				
		}
		if(temp.equals("failure"))
		{
			ResponseMessage m=new ResponseMessage();
			m.setType("Failure");
			m.setHeading("Admin Login Failure");
			m.setContent("<li>Admin id missing or invalid !<br><li>Admin Password is missing or invalid !<br>");
			m.setFooting("Sorry - Area Restricted to Administrator !");
			request.getSession().setAttribute("response", m);
		}
		return (mapping.findForward (temp));
	}
}










 
