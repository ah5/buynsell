package com.buynsell.adminlogin;

import org.apache.struts.action.ActionForm;

public class AdminSelectAuctionForm extends ActionForm {
	String auctionSelected = null;

	public String getAuctionSelected() {
		return this.auctionSelected;
	}

	public void setAuctionSelected(String auctionSelected) {
		this.auctionSelected = auctionSelected;
	}

	public void reset() {
		setAuctionSelected(null);
	}

	public boolean validator() {
		if ((getAuctionSelected() == null) || (getAuctionSelected().length() < 1))
			return true;
		else
			return false;
	}
}
