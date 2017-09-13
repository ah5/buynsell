package com.buynsell.accountoperations.buyeroperations;

import org.apache.struts.action.ActionForm;

public class SelectAuctionForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2291023911111615155L;
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
