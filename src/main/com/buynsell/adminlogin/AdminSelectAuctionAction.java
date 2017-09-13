package com.buynsell.adminlogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.Auction;
import com.buynsell.databaseconnection.JdbcData;

public class AdminSelectAuctionAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AdminSelectAuctionForm obj = (AdminSelectAuctionForm) form;
		Auction auction1 = JdbcData.loadAuctionExplicit(obj.getAuctionSelected());
		request.getSession().setAttribute("selectedauction", auction1);
		obj.reset();
		return (mapping.findForward("success"));
	}
}