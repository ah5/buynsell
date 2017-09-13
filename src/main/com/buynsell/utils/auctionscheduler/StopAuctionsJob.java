package com.buynsell.utils.auctionscheduler;

import java.util.ArrayList;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.buynsell.businessobjects.Auction;
import com.buynsell.businessobjects.Bid;
import com.buynsell.businessobjects.BidDetails;
import com.buynsell.businessobjects.Catalog;
import com.buynsell.businessobjects.Users;
import com.buynsell.databaseconnection.JdbcData;
import com.buynsell.utils.Mailer;
import com.buynsell.utils.solvers.AuctionSolver;

public class StopAuctionsJob implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jd = context.getJobDetail();
		JobDataMap jdm = jd.getJobDataMap();

		String auctionid = jdm.getString("auctionID");
		Auction auction = JdbcData.loadAuction(jdm.getString("catalogID"));
		Catalog catalog = JdbcData.loadCatalog(jdm.getString("catalogID"));
		String userid = catalog.getUserid();
		ArrayList allProducts = JdbcData.loadProduct(jdm.getString("catalogID"));

		AuctionSolver solvingObject = new AuctionSolver();
		ArrayList winningBids = solvingObject.solve(catalog.getCatalogid());

		for (int i = 0; i < winningBids.size(); i++) {
			Bid bid = (Bid) winningBids.get(i);
			Users user = JdbcData.loadUser(bid.getUserid());
			String email = user.getEmailid();
			ArrayList bidDet = JdbcData.loadBidDetails(bid.getBidid());

			Mailer mailAuctionAnnounce = new Mailer(null, null);
			String subject = "com.buynsell Mailer Program : AUCTION TERMINATED !";
			String contentBeforeImage = "<html><head></head><body>"
					+ "<TABLE WIDTH=780 BORDER=0 CELLPADDING=0 CELLSPACING=0>" + "<tr><td colspan='100%'>";
			String image = "c:/header.gif";
			String contentAfterImage = "</td></tr><tr><td colspan='100%'><font color='white'>YOUR BID HAS BEEN A WINNER IN OUR AUCTIONS</td></tr>"
					+ "<tr bgcolor='#B00000'><td><font color='white' face='tahoma' size='2'><b>Bid Id :"
					+ "</td><td><font color='white' face='tahoma' size='2'><b>" + bid.getBidid()
					+ "</td><td></td><td></td></tr>"
					+ "<tr><td><font face='tahoma' size='2'><b>Auction id :</td><td><font face='tahoma' size='2'>"
					+ bid.getAuctionid() + "</td>"
					+ "<td><font face='tahoma' size='2'><b>Bid Price :</td><td><font face='tahoma' size='2'>"
					+ bid.getBidprice() + "</td></tr>";
			if (bidDet.size() > 0) {
				String col = "#E8E8E8";
				for (int j = 0; j < bidDet.size(); j++) {
					BidDetails det = (BidDetails) bidDet.get(j);
					if (col.equals("#E8E8E8"))
						col = "#F0F0F0";
					else
						col = "#E8E8E8";
					contentAfterImage = contentAfterImage + "<tr bgcolor='" + col
							+ "'><td colspan='4'><hr></td></tr><tr bgcolor='" + col + "'>"
							+ "<td><font face='tahoma' size='2'>Product Id :</td><td><font face='tahoma' size='2'>"
							+ det.getProductid() + "</td>"
							+ "<td><font face='tahoma' size='2'>Bid Qty :</td><td><font face='tahoma' size='2'>"
							+ det.getBidqty() + "</td></tr>";
				}
			}
			contentAfterImage = contentAfterImage
					+ "<tr><td colspan='100%' align='center'><font face='tahoma' size='2'>"
					+ "Your bid has been succesful in our Auctions !<br>"
					+ "All other proceedings regarding payment will be notified to you at the earliest !<br>"
					+ "Thank You !</td></tr></table></body></html>";
			mailAuctionAnnounce.sendMail(user.getEmailid(), subject, contentBeforeImage, image, contentAfterImage);
		}

		/*
		 * find out the loosing bids also then send corres mails to those users
		 **/

	}
}
