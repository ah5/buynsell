package buyNsell.AdminLogin;

import buyNsell.BusinessObjects.*;
import buyNsell.DatabaseConnection.*;

import org.apache.struts.action.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.*;

public class AdminSelectUserAction extends Action
{
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response)
								throws Exception
	{
		AdminSelectUserForm obj=(AdminSelectUserForm) form;
		Users user1=JdbcData.loadUser(obj.getUserSelected());
		request.getSession().setAttribute("selecteduser", user1);
		obj.reset();
		return (mapping.findForward ("success"));
	}
}