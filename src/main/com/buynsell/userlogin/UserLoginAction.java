package com.buynsell.userlogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.ResponseMessage;
import com.buynsell.businessobjects.Users;
import com.buynsell.databaseconnection.JdbcData;

public class UserLoginAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserLoginForm login = (UserLoginForm) form;
		String userid = login.getUserid();
		String pass = login.getPass();

		Users user = new Users();
		user = JdbcData.loadUser(userid);
		String temp;

		if (login.validator()) {
			login.reset();
			temp = "failure";
		} else {
			login.reset();
			if (user == null)
				temp = "failure";
			else {
				if (pass.equals(user.getPassword())) {
					request.getSession().setAttribute("user", user);
					temp = "success";
				} else
					temp = "failure";
			}
		}
		if (temp.equals("failure")) {
			ResponseMessage m = new ResponseMessage();
			m.setType("Failure");
			m.setHeading("Login Failure - Invalid User");
			m.setContent(
					"<li>'BUY N SELL' Account username missing or invalid !<br><li>Account password is missing or invalid !<br>");
			m.setFooting("Sorry ! Do Try Once Again !");
			request.getSession().setAttribute("response", m);
		}
		return (mapping.findForward(temp));
	}
}
