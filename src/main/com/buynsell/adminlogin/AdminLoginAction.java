package com.buynsell.adminlogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.Admin;
import com.buynsell.businessobjects.ResponseMessage;
import com.buynsell.databaseconnection.JdbcData;
import com.buynsell.userlogin.UserLoginForm;

public class AdminLoginAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserLoginForm login = (UserLoginForm) form;
		String userid = login.getUserid();
		String pass = login.getPass();

		Admin admin = new Admin();
		admin = JdbcData.loadAdmin(userid, pass);
		String temp;

		if (login.validator()) {
			login.reset();
			temp = "failure";
		} else {
			login.reset();
			if (admin == null)
				temp = "failure";
			else {
				request.getSession().setAttribute("admin", admin);
				temp = "success";
			}
		}
		if (temp.equals("failure")) {
			ResponseMessage m = new ResponseMessage();
			m.setType("Failure");
			m.setHeading("Admin Login Failure");
			m.setContent("<li>Admin id missing or invalid !<br><li>Admin Password is missing or invalid !<br>");
			m.setFooting("Sorry - Area Restricted to Administrator !");
			request.getSession().setAttribute("response", m);
		}
		return (mapping.findForward(temp));
	}
}
