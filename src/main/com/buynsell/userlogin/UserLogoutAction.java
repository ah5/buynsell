package com.buynsell.userlogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.ResponseMessage;

public class UserLogoutAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResponseMessage m = new ResponseMessage();
		if (request.getSession().getAttribute("user") != null) {
			request.getSession().removeAttribute("user");
			m.setType("Success");
			m.setHeading("Logout Successful");
			m.setContent("You have succesfully logged out.");
			m.setFooting("Thank You !");
		} else {
			m.setType("Failure");
			m.setHeading("Logout Failure - Session Invalid");
			m.setContent("This is not a place to play around !");
			m.setFooting("Session Invalid !");
		}
		request.getSession().setAttribute("response", m);
		return (mapping.findForward("response"));
	}
}
