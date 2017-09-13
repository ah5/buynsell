package com.buynsell.adminlogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.Users;

public class AdminDeleteUserAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Users u = (Users) request.getSession().getAttribute("selecteduser");

		String temp = "DELETE";
		request.getSession().setAttribute("message", temp);

		return (mapping.findForward("success"));
	}
}