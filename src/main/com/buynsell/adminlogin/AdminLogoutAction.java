package com.buynsell.adminlogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.ResponseMessage;

public class AdminLogoutAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResponseMessage m = new ResponseMessage();
		if (request.getSession().getAttribute("admin") != null) {
			request.getSession().removeAttribute("admin");
			m.setType("Success");
			m.setHeading("Admin Logout Successful");
			m.setContent("All admin rights taken off <br> Normal State Loaded");
			m.setFooting("Thank You!");
		} else {
			m.setType("Failure");
			m.setHeading("Admin Logout Failure - Session Invalid");
			m.setContent("This is not a place to play around !");
			m.setFooting("Session Invalid !");
		}
		request.getSession().setAttribute("response", m);
		return (mapping.findForward("response"));
	}
}
