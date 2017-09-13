package com.buynsell.accountoperations.buyeroperations.bidposting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.Auction;
import com.buynsell.businessobjects.Catalog;
import com.buynsell.businessobjects.ResponseMessage;
import com.buynsell.businessobjects.Users;
import com.buynsell.databaseconnection.JdbcData;
import com.buynsell.databaseconnection.JdbcUtil;
import com.buynsell.utils.GenerateID;

public class BidPostAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BidPostForm bidform = (BidPostForm) form;
		ResponseMessage m = new ResponseMessage();

		if (!bidform.validator()) {
			int noOfBids = Integer.parseInt(bidform.getno());
			Catalog c = (Catalog) request.getSession().getAttribute("selectedcatalog");
			String catID = c.getCatalogid();
			Users u = (Users) request.getSession().getAttribute("user");
			Auction auction = JdbcData.loadAuction(catID);
			GenerateID obj = new GenerateID();
			String bidid = obj.getBidID();

			String sql = "insert into bid values('" + bidid + "','" + auction.getAuctionid() + "','" + u.getUserid()
					+ "','" + bidform.getbp() + "')";
			JdbcUtil.updtQuery(sql);

			if (noOfBids > 0) {
				sql = "insert into biddetails values('" + bidid + "','" + bidform.getpi0() + "','" + bidform.getbq0()
						+ "')";
				JdbcUtil.updtQuery(sql);
				if (noOfBids > 1) {
					sql = "insert into biddetails values('" + bidid + "','" + bidform.getpi1() + "','"
							+ bidform.getbq1() + "')";
					JdbcUtil.updtQuery(sql);
					if (noOfBids > 2) {
						sql = "insert into biddetails values('" + bidid + "','" + bidform.getpi2() + "','"
								+ bidform.getbq2() + "')";
						JdbcUtil.updtQuery(sql);
						if (noOfBids > 3) {
							sql = "insert into biddetails values('" + bidid + "','" + bidform.getpi3() + "','"
									+ bidform.getbq3() + "')";
							JdbcUtil.updtQuery(sql);
							if (noOfBids > 4) {
								sql = "insert into biddetails values('" + bidid + "','" + bidform.getpi4() + "','"
										+ bidform.getbq4() + "')";
								JdbcUtil.updtQuery(sql);
							}
						}
					}
				}
			}

			m.setType("Success");
			m.setHeading("Bidding Success");
			m.setContent(
					"You have succesfully placed your bid and can view your <BR> bid items from the link - View Auctioned items - <BR> The details regarding the status of your success in the auction <BR> will be intimated through mail when the auction concludes.");
			m.setFooting("Thank You !");
		} else {
			m.setType("Failure");
			m.setHeading("Bidding Failure");
			m.setContent(
					"You have failed in placed your bid. <BR> You might have missed out on any of the following data :<br> <li>You might have forgot to mention the Bidding Quantity.<br><li>You might have not entered the Package Bidding Price.");
			m.setFooting("Sorry ! Try Once Again !");
		}
		request.getSession().setAttribute("response", m);
		return (mapping.findForward("response"));
	}
}