package com.buynsell.databaseconnection;

import java.util.ArrayList;

import com.buynsell.businessobjects.Admin;
import com.buynsell.businessobjects.Auction;
import com.buynsell.businessobjects.Bid;
import com.buynsell.businessobjects.BidDetails;
import com.buynsell.businessobjects.Catalog;
import com.buynsell.businessobjects.CreditCard;
import com.buynsell.businessobjects.Product;
import com.buynsell.businessobjects.Users;

@SuppressWarnings("rawtypes")
public class JdbcData {
	public static Users loadUser(String userID) {
		Users users = null;
		String sql = "select * from users where UserID='" + userID + "'";
		ArrayList<?> arr = JdbcUtil.exeQuery(sql);
		if (arr.size() > 0) {
			ArrayList<?> raw = (ArrayList<?>) arr.get(0);
			users = new Users(raw);
		}
		return users;
	}

	public static ArrayList<Users> loadOtherUsers(String userID) {
		Users users = null;
		String sql = "select * from users where UserID not like '" + userID + "'";
		ArrayList<?> arr = JdbcUtil.exeQuery(sql);
		ArrayList<Users> result = new ArrayList<Users>();
		if (arr.size() > 0) {
			for (int i = 0; i < arr.size(); i++) {
				ArrayList<?> raw = (ArrayList<?>) arr.get(0);
				users = new Users(raw);
				result.add(users);
			}
		}
		return result;
	}

	public static CreditCard loadCreditCardDetails(String userID) {
		CreditCard cc = null;
		String sql = "select * from creditcarddetails where UserID='" + userID + "'";
		ArrayList arr = JdbcUtil.exeQuery(sql);
		if (arr.size() > 0) {
			ArrayList<?> raw = (ArrayList<?>) arr.get(0);
			cc = new CreditCard(raw);
		}
		return cc;
	}
	
	public static Admin loadAdmin(String adminID, String adminPass) {
		Admin admin = null;
		String sql = "select * from admin where AdminID='" + adminID + "' and AdminPassword='" + adminPass + "'";
		ArrayList arr = JdbcUtil.exeQuery(sql);
		if (arr.size() > 0) {
			ArrayList<?> raw = (ArrayList<?>) arr.get(0);
			admin = new Admin(raw);
		}
		return admin;
	}
	
	public static Catalog loadCatalog(String catalogID) {
		String sql = "select * from catalog where CatalogID like '" + catalogID + "'";
		ArrayList arr = JdbcUtil.exeQuery(sql);
		Catalog catalog = null;
		if (arr.size() > 0) {
			ArrayList<?> raw = (ArrayList<?>) arr.get(0);
			catalog = new Catalog(raw);
		}
		return catalog;
	}
	
	public static ArrayList loadCatalog(String userID, boolean userCase) {
		String sql = null;
		if (userCase == true) // if user is seller
			sql = "select * from catalog where UserID like '" + userID + "'";
		else // else user is buyer
			sql = "select * from catalog where UserID not like '" + userID + "'";

		ArrayList<Catalog> result = new ArrayList<Catalog>();
		ArrayList arr = JdbcUtil.exeQuery(sql);
		Catalog catalog = null;
		if (arr.size() > 0) {
			for (int i = 0; i < arr.size(); i++) {
				ArrayList<?> raw = (ArrayList<?>) arr.get(i);
				catalog = new Catalog(raw);
				result.add(catalog);
			}
		}
		return result;
	}

	public static ArrayList loadAuctions() {
		Auction auction = null;
		String sql = "select * from auction";
		ArrayList arr = JdbcUtil.exeQuery(sql);
		ArrayList<Auction> result = new ArrayList<Auction>();
		if (arr.size() > 0) {

			for (int i = 0; i < arr.size(); i++) {
				ArrayList<?> raw = (ArrayList<?>) arr.get(i);
				auction = new Auction(raw);
				result.add(auction);
			}
		}
		return result;
	}

	public static Auction loadAuctionExplicit(String auctionID) {
		Auction auction = null;
		String sql = "select * from auction where AuctionID='" + auctionID + "'";
		ArrayList arr = JdbcUtil.exeQuery(sql);
		if (arr.size() > 0) {
			ArrayList<?> raw = (ArrayList<?>) arr.get(0);
			auction = new Auction(raw);
		}
		return auction;
	}

	public static Auction loadAuction(String catalogID) {
		Auction auction = null;
		String sql = "select * from auction where CatalogID='" + catalogID + "'";
		ArrayList arr = JdbcUtil.exeQuery(sql);
		if (arr.size() > 0) {
			ArrayList<?> raw = (ArrayList<?>) arr.get(0);
			auction = new Auction(raw);
		}
		return auction;
	}

	public static ArrayList<Product> loadProduct(String catalogID) {
		Product product = null;
		ArrayList<Product> result = new ArrayList<Product>();
		String sql = "select * from product where CatalogID='" + catalogID + "'";
		ArrayList arr = JdbcUtil.exeQuery(sql);
		if (arr.size() > 0) {
			for (int i = 0; i < arr.size(); i++) {
				ArrayList<?> raw = (ArrayList<?>) arr.get(i);
				product = new Product(raw);
				result.add(product);
			}
		}
		return result;
	}

	public static ArrayList loadSimilarProducts(String name) {
		String temp;
		if (name.length() > 2)
			temp = name.substring(0, 2);
		else
			temp = name.substring(0, 1);
		Product product = null;
		ArrayList<Product> result = new ArrayList<Product>();
		String sql = "select * from product where ProductName like '" + temp + "%'";
		ArrayList arr = JdbcUtil.exeQuery(sql);
		if (arr.size() > 0) {
			for (int i = 0; i < arr.size(); i++) {
				ArrayList<?> raw = (ArrayList<?>) arr.get(i);
				product = new Product(raw);
				result.add(product);
			}
		}
		return result;
	}

	public static ArrayList loadCategoryProducts(String catname) {
		Product product = null;
		ArrayList<Product> products = new ArrayList<Product>();
		String sql = "select * from product where Category like '" + catname + "'";
		ArrayList arr = JdbcUtil.exeQuery(sql);
		if (arr.size() > 0) {
			for (int i = 0; i < arr.size(); i++) {
				ArrayList<?> raw = (ArrayList<?>) arr.get(i);
				product = new Product(raw);
				products.add(product);
			}
		}
		return products;
	}

	public static ArrayList<Bid> loadBids(String auctionID) {
		Bid bid = null;
		ArrayList<Bid> result = new ArrayList<Bid>();
		String sql = "select * from bid where AuctionID='" + auctionID + "'";
		ArrayList arr = JdbcUtil.exeQuery(sql);
		if (arr.size() > 0) {
			for (int i = 0; i < arr.size(); i++) {
				ArrayList<?> raw = (ArrayList<?>) arr.get(i);
				bid = new Bid(raw);
				result.add(bid);
			}
		}
		return result;
	}

	public static ArrayList<BidDetails> loadBidDetails(String bidID) {
		BidDetails biddetails = null;
		ArrayList<BidDetails> result = new ArrayList<BidDetails>();
		String sql = "select * from biddetails where BidID='" + bidID + "'";
		ArrayList arr = JdbcUtil.exeQuery(sql);
		if (arr.size() > 0) {
			for (int i = 0; i < arr.size(); i++) {
				ArrayList<?> raw = (ArrayList<?>) arr.get(i);
				biddetails = new BidDetails(raw);
				result.add(biddetails);
			}
		}
		return result;
	}

	public static ArrayList loadAllAuctionsToBeActive() {
		Auction auction = null;
		String today = JdbcUtil.fromJavaDateToSQLString(new java.util.Date());
		ArrayList<Auction> result = new ArrayList<Auction>();
		String sql = "select * from auction where StartingDate < '" + today + "' and EndingDate > '" + today + "'";
		ArrayList arr = JdbcUtil.exeQuery(sql);
		if (arr.size() > 0) {
			for (int i = 0; i < arr.size(); i++) {
				ArrayList<?> raw = (ArrayList<?>) arr.get(i);
				auction = new Auction(raw);
				result.add(auction);
			}
		}
		return result;
	}

	public static ArrayList loadAllAuctionsToBeInActive() {
		Auction auction = null;
		String today = JdbcUtil.fromJavaDateToSQLString(new java.util.Date());
		ArrayList<Auction> result = new ArrayList<Auction>();
		String sql = "select * from auction where EndingDate <= '" + today + "'";
		ArrayList arr = JdbcUtil.exeQuery(sql);
		if (arr.size() > 0) {
			for (int i = 0; i < arr.size(); i++) {
				ArrayList<?> raw = (ArrayList<?>) arr.get(i);
				auction = new Auction(raw);
				result.add(auction);
			}
		}
		return result;
	}
}
