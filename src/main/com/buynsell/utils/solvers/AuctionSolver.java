package com.buynsell.utils.solvers;

import java.util.ArrayList;

import com.buynsell.businessobjects.Auction;
import com.buynsell.businessobjects.Bid;
import com.buynsell.businessobjects.BidDetails;
import com.buynsell.businessobjects.Catalog;
import com.buynsell.businessobjects.Product;
import com.buynsell.databaseconnection.JdbcData;

public class AuctionSolver {
	Auction auction = null;
	Catalog catalog = null;
	Product product = null;
	Bid bid = null;
	BidDetails bidDetail = null;

	ArrayList<Product> productsInCatalog = null;
	ArrayList<Bid> allBids = null;
	ArrayList<BidDetails> bidDetails = null;
	ArrayList<Bid> winningBids = null;

	ArrayList<?> raw = null;

	int topBidLocator = 0;
	int topBidPrice = 0;
	boolean productAvailibility = true;

	public AuctionSolver() {
	}

	public ArrayList<?> solve(String catalogID) {
		// loading auction details , catalog details , product details , bids
		// for concerned auction
		auction = JdbcData.loadAuction(catalogID);
		catalog = JdbcData.loadCatalog(catalogID);
		productsInCatalog = JdbcData.loadProduct(catalogID);
		allBids = JdbcData.loadBids(auction.getAuctionid());
		winningBids = new ArrayList<Bid>();

		while (allBids.size() > 0) {
			System.out.println("-------------------------");
			System.out.println("all bids=" + allBids.size());

			// finding bid with highest price
			topBidPrice = 0;
			for (int i = 0; i < allBids.size(); i++) {
				bid = (Bid) allBids.get(i);
				if (Integer.parseInt(bid.getBidprice()) > topBidPrice) {
					topBidPrice = Integer.parseInt(bid.getBidprice());
					topBidLocator = i;
				}
			}

			System.out.println("topBidLocator=" + topBidLocator);

			productAvailibility = true;
			// checking availability for requested items
			bid = (Bid) allBids.get(topBidLocator);
			System.out.println("check pt 1");
			bidDetails = JdbcData.loadBidDetails(bid.getBidid());
			for (int i = 0; i < bidDetails.size(); i++) {
				bidDetail = (BidDetails) bidDetails.get(i);
				for (int j = 0; j < productsInCatalog.size(); j++) {
					product = (Product) productsInCatalog.get(j);
					if ((bidDetail.getProductid().equals(product.getProductid()))
							&& (Integer.parseInt(bidDetail.getBidqty())) > (Integer.parseInt(product.getQty()))) {
						productAvailibility = false;
					}
				}
			}

			// adding bid as winning bid if item availibility success
			if (productAvailibility == true) {
				System.out.println("product is available");
				// removing product qty from existing product
				for (int i = 0; i < bidDetails.size(); i++) {
					bidDetail = (BidDetails) bidDetails.get(i);
					for (int j = 0; j < productsInCatalog.size(); j++) {
						product = (Product) productsInCatalog.get(j);
						if (bidDetail.getProductid().equals(product.getProductid())) {
							int newqty = (Integer.parseInt(product.getQty()) - Integer.parseInt(bidDetail.getBidqty()));
							product.setQty(String.valueOf(newqty));
							productsInCatalog.set(j, product);
							System.out.println("product " + j + "removal success");
						}
					}
				}
				System.out.println("adding bid to winning bid set");
				winningBids.add(bid);
			}

			// removing current winning bid from the set of bids
			System.out.println("removing bid from all bids");
			allBids.remove(topBidLocator);
		}

		// returning solution in the form of the winning bids
		return winningBids;
	}

	public static void main(String args[]) {
		AuctionSolver as = new AuctionSolver();
		ArrayList wins = as.solve("C18526");
	}
}