package com.buynsell.itemsearch;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.Product;
import com.buynsell.businessobjects.ResponseMessage;
import com.buynsell.databaseconnection.JdbcData;

@SuppressWarnings("rawtypes")
public class ItemSearchAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ItemSearchForm item = (ItemSearchForm) form;
		String temp;
		Product product = null;

		if (item.validator())
			temp = "failure";
		else {
			ArrayList simProducts = JdbcData.loadSimilarProducts(item.getItemName());
			if (simProducts.size() > 0) {
				temp = "success";
				product = (Product) simProducts.get(0);
			} else
				temp = "failure";
		}

		if (temp.equals("failure")) {
			ResponseMessage m = new ResponseMessage();
			m.setType("Failure");
			m.setHeading("Search Failure - Invalid Search");
			m.setContent(
					"Either of the following errors might have occured :<br><li>Either the item name has not been entered !<br><li>Or the Item doesn't exist in database !");
			m.setFooting("Sorry ! Do Try Once Again !");
			request.getSession().setAttribute("response", m);
		} else {
			product.setName(item.getItemName());
			request.getSession().setAttribute("itemSearched", product);
		}

		item.reset();
		return (mapping.findForward(temp));
	}
}
