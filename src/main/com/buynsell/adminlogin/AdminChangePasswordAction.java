package com.buynsell.adminlogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.accountoperations.ChangePasswordForm;
import com.buynsell.businessobjects.Admin;
import com.buynsell.databaseconnection.JdbcUtil;

public class AdminChangePasswordAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ChangePasswordForm p = (ChangePasswordForm) form;
		String temp;

		if (p.validator()) {
			p.reset();
			temp = "failure";
		} else {
			Admin a = (Admin) request.getSession().getAttribute("admin");
			if (a.getPass().equals(p.getOld())) {
				String q = "update admin set AdminPassword='" + p.getNew1() + "' where AdminID like'" + a.getId() + "'";
				JdbcUtil.updtQuery(q);
				temp = "success";
			} else
				temp = "failure";
			p.reset();
		}
		request.getSession().setAttribute("message", temp);
		return (mapping.findForward("success"));
	}
}
