package com.buynsell.adminlogin;

import buyNsell.AccountOperations.*;
import buyNsell.BusinessObjects.*;
import buyNsell.DatabaseConnection.*;

import org.apache.struts.action.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AdminChangePasswordAction extends Action
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
			Admin a=(Admin)request.getSession().getAttribute("admin");
			if(a.getPass().equals(p.getOld()))
			{
				String q="update admin set AdminPassword='"+ p.getNew1() +"' where AdminID like'"+ a.getId() +"'";
				JdbcUtil.updtQuery(q);
				temp="success";
			}
			else
				temp="failure";
			p.reset();
		}
		request.getSession().setAttribute("message", temp);
		return (mapping.findForward ("success"));
	}
}
