package com.buynsell.itemsearch;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.Auction;
import com.buynsell.businessobjects.Catalog;
import com.buynsell.businessobjects.Product;
import com.buynsell.businessobjects.ResponseMessage;
import com.buynsell.databaseconnection.JdbcData;

@SuppressWarnings("rawtypes")
public class ListCategoryAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ListCategoryForm selected = (ListCategoryForm) form;
		String cat = selected.getItemCategory();
		selected.reset();
		ArrayList categoryRelatedProducts = JdbcData.loadCategoryProducts(cat);
		ArrayList<Catalog> categoryRelatedCatalogs = new ArrayList<Catalog>();
		ArrayList<Auction> categoryRelatedAuctions = new ArrayList<Auction>();
		if (categoryRelatedProducts.size() > 0) {
			for (int i = 0; i < categoryRelatedProducts.size(); i++) {
				Product p = (Product) categoryRelatedProducts.get(i);
				String catalogid = p.getCatalogid();
				Catalog catalog = JdbcData.loadCatalog(catalogid);
				Auction auction = JdbcData.loadAuction(catalogid);
				if (categoryRelatedCatalogs.size() > 0) {
					boolean exists = false;
					for (int j = 0; j < categoryRelatedCatalogs.size(); j++) {
						Catalog temp = (Catalog) categoryRelatedCatalogs.get(j);
						if (temp.getCatalogid().equals(catalog.getCatalogid()))
							exists = true;
					}
					if (exists == false) {
						categoryRelatedCatalogs.add(catalog);
						categoryRelatedAuctions.add(auction);
					}
				} else {
					categoryRelatedCatalogs.add(catalog);
					categoryRelatedAuctions.add(auction);
				}
			}
			request.getSession().setAttribute("category", cat);
			request.getSession().setAttribute("catProducts", categoryRelatedProducts);
			request.getSession().setAttribute("catCatalogs", categoryRelatedCatalogs);
			request.getSession().setAttribute("catAuctions", categoryRelatedAuctions);
			return (mapping.findForward("success"));
		} else {
			ResponseMessage m = new ResponseMessage();
			m.setType("Failure");
			if (cat == null) {
				m.setHeading("Category-wise Listing");
				m.setContent("<li>You haven't selected the Category - pls select the category !");
				m.setFooting("Try the Listing Once More - Thank You !");
			} else {
				m.setHeading("Category Listing for - " + cat);
				m.setContent("<li> There is no items existing for the Category you selected !");
				m.setFooting("Thank You !");
			}
			request.getSession().setAttribute("response", m);
			return (mapping.findForward("failure"));
		}
	}
}
