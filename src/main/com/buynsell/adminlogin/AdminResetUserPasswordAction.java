package com.buynsell.adminlogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.Users;
import com.buynsell.databaseconnection.JdbcUtil;

public class AdminResetUserPasswordAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Users u = (Users) request.getSession().getAttribute("selecteduser");
		String temp = null;
		if (u != null) {
			String q = "update users set Password='' where UserID like '" + u.getUserid() + "'";
			JdbcUtil.updtQuery(q);
			temp = "reset success";
		} else
			temp = "reset failure";
		request.getSession().setAttribute("message", temp);
		return (mapping.findForward("success"));
	}
}