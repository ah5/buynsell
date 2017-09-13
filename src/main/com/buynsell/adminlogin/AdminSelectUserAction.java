package com.buynsell.adminlogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.Users;
import com.buynsell.databaseconnection.JdbcData;

public class AdminSelectUserAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AdminSelectUserForm obj = (AdminSelectUserForm) form;
		Users user1 = JdbcData.loadUser(obj.getUserSelected());
		request.getSession().setAttribute("selecteduser", user1);
		obj.reset();
		return (mapping.findForward("success"));
	}
}