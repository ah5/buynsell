package com.buynsell.accountoperations.selleroperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.Catalog;
import com.buynsell.businessobjects.ResponseMessage;

public class SellerSelectAuctionAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SellerSelectAuctionForm obj = (SellerSelectAuctionForm) form;

		if (obj.validator()) {
			ResponseMessage m = new ResponseMessage();
			m.setType("Failure");
			m.setHeading("Auction Status Fetching Failure");
			m.setContent("<li>You have not selected the auction <br>for which the status is required !");
			m.setFooting("Sorry ! Try Once Again !");
			request.getSession().setAttribute("response", m);
			return (mapping.findForward("failure"));
		} else {
			Catalog catalog1 = new Catalog();
			catalog1.setCatalogid(obj.getAuctionSelected());
			request.getSession().setAttribute("sellerselectedcatalog", catalog1);
			return (mapping.findForward("success"));
		}
	}
}