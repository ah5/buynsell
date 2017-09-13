package com.buynsell.accountoperations.buyeroperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.Catalog;
import com.buynsell.businessobjects.ResponseMessage;

public class SelectAuctionAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SelectAuctionForm obj = (SelectAuctionForm) form;
		if (obj.validator()) {
			ResponseMessage m = new ResponseMessage();
			m.setType("Failure");
			m.setHeading("Auction Fetching Failure for User Bidding");
			m.setContent("<li>You have not selected the auction <br>for which you want to start bidding !");
			m.setFooting("Sorry ! Try Once More !");
			request.getSession().setAttribute("response", m);
			return (mapping.findForward("failure"));
		} else {
			Catalog catalog1 = new Catalog();
			catalog1.setCatalogid(obj.getAuctionSelected());
			request.getSession().setAttribute("selectedcatalog", catalog1);
			return (mapping.findForward("success"));
		}

	}
}