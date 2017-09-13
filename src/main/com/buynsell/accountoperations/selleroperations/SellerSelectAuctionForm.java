package com.buynsell.accountoperations.selleroperations;

import org.apache.struts.action.ActionForm;

public class SellerSelectAuctionForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7844221848657322652L;
	String auctionSelected = null;

	public String getAuctionSelected() {
		return this.auctionSelected;
	}

	public void setAuctionSelected(String auctionSelected) {
		this.auctionSelected = auctionSelected;
	}

	public boolean validator() {
		if ((getAuctionSelected() == null) || (getAuctionSelected().length() < 1))
			return true;
		else
			return false;
	}
}
