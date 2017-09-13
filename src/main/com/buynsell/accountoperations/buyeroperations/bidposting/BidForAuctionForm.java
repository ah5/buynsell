package com.buynsell.accountoperations.buyeroperations.bidposting;

import org.apache.struts.action.ActionForm;

public class BidForAuctionForm extends ActionForm {
	String cid;

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
}