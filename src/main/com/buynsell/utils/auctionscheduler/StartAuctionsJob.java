package com.buynsell.utils.auctionscheduler;

import java.util.ArrayList;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.buynsell.businessobjects.Auction;
import com.buynsell.businessobjects.Catalog;
import com.buynsell.businessobjects.Product;
import com.buynsell.businessobjects.Users;
import com.buynsell.databaseconnection.JdbcData;
import com.buynsell.utils.Mailer;

public class StartAuctionsJob implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jd = context.getJobDetail();
		JobDataMap jdm = jd.getJobDataMap();

		String auctionid = jdm.getString("auctionID");
		String catalogid = jdm.getString("catalogID");

		Auction auction = JdbcData.loadAuction(catalogid);
		Catalog catalog = JdbcData.loadCatalog(catalogid);

		Users users = JdbcData.loadUser(catalog.getUserid());
		String email = users.getEmailid();

		ArrayList allProducts = JdbcData.loadProduct(catalogid);
		ArrayList otherUsers = JdbcData.loadOtherUsers(catalog.getUserid());

		System.out.println("Auction id =" + auctionid);

		Mailer mailAuctionAnnounce = new Mailer(null, null);
		String subject = "com.buynsell Mailer Program : NEW AUCTION ANNOUNCED !";
		String contentBeforeImage = "<html><head></head><body>"
				+ "<TABLE WIDTH=780 BORDER=0 CELLPADDING=0 CELLSPACING=0>" + "<tr><td colspan='100%'>";
		String image = "c:/header.gif";
		String contentAfterImage = "</td></tr><tr><td colspan='100%'><font color='white'>FILLER</td></tr>"
				+ "<tr bgcolor='#B00000'><td><font color='white' face='tahoma' size='2'><b>Auction Id :"
				+ "</td><td><font color='white' face='tahoma' size='2'><b>" + auction.getAuctionid()
				+ "</td><td></td><td></td></tr>"
				+ "<tr><td><font face='tahoma' size='2'><b>Started On :</td><td><font face='tahoma' size='2'>"
				+ auction.getStartingdate() + "</td>"
				+ "<td><font face='tahoma' size='2'><b>Ends On :</td><td><font face='tahoma' size='2'>"
				+ auction.getEndingdate() + "</td></tr>"
				+ "<tr><td><font face='tahoma' size='2'><b>Catalog Id :</td><td><font face='tahoma' size='2'>"
				+ catalog.getCatalogid() + "</td></tr>"
				+ "<tr><td><font face='tahoma' size='2'><b>Starting Price :</td><td><font face='tahoma' size='2'>"
				+ catalog.getStartingprice() + "</td>"
				+ "<td><font face='tahoma' size='2'><b>Bid Increment :</td><td><font face='tahoma' size='2'>"
				+ catalog.getBidincrement() + "</td></tr>";

		Product product = null;
		if (allProducts.size() > 0) {
			String col = "#E8E8E8";
			for (int j = 0; j < allProducts.size(); j++) {
				product = (Product) allProducts.get(j);
				if (col.equals("#E8E8E8"))
					col = "#F0F0F0";
				else
					col = "#E8E8E8";
				contentAfterImage = contentAfterImage + "<tr bgcolor='" + col
						+ "'><td colspan='4'><hr></td></tr><tr bgcolor='" + col + "'>"
						+ "<td><font face='tahoma' size='2'>Product Id :</td><td><font face='tahoma' size='2'>"
						+ product.getProductid() + "</td><td></td><td></td></tr><tr bgcolor='" + col + "'>"
						+ "<td><font face='tahoma' size='2'>Product Name :</td><td><font face='tahoma' size='2'>"
						+ product.getName() + "</td>"
						+ "<td><font face='tahoma' size='2'>Description :</td><td><font face='tahoma' size='2'>"
						+ product.getDesc() + "</td>" + "<tr bgcolor='" + col + "'>"
						+ "<td><font face='tahoma' size='2'>Category :</td><td><font face='tahoma' size='2'>"
						+ product.getCat() + "</td>"
						+ "<td><font face='tahoma' size='2'>Quantity :</td><td><font face='tahoma' size='2'>"
						+ product.getQty() + "</td></tr>";
			}
		}
		contentAfterImage = contentAfterImage + "<tr><td colspan='100%' align='center'><font face='tahoma' size='2'>"
				+ "The Auction has been hosted just now ! You are free to logon <br>"
				+ "to com.buynsell and post your bids anytime you feel like ! <br>"
				+ "Thank You !</td></tr></table></body></html>";

		// notifications to all other users
		for (int i = 0; i < otherUsers.size(); i++) {
			Users user = (Users) otherUsers.get(i);
			mailAuctionAnnounce.sendMail(user.getEmailid(), subject, contentBeforeImage, image, contentAfterImage);
		}
		// anouncement of auction notification to the seller
		contentAfterImage = "</td></tr><tr><td colspan='100%'><font color='white'>FILLER</td></tr>"
				+ "<tr><td colspan='100%' align='center'><font face='tahoma' size='2'>"
				+ "Your auction has been announced to all the users of com.buynsell !<br>"
				+ "Thank You !</td></tr></table></body></html>";
		mailAuctionAnnounce.sendMail(email, subject, contentBeforeImage, image, contentAfterImage);
	}
}
