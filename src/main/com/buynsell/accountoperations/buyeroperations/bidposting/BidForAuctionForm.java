package com.buynsell.accountoperations.buyeroperations.bidposting;

import org.apache.struts.action.ActionForm;

public class BidForAuctionForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3831195497162980271L;
	String cid;

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
}