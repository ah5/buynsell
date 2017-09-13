package com.buynsell.accountoperations.selleroperations;

import org.apache.struts.action.ActionForm;

public class SellerSelectAuctionForm extends ActionForm {
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
