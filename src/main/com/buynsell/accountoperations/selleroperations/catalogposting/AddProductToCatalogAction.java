package com.buynsell.accountoperations.selleroperations.catalogposting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.Catalog;
import com.buynsell.databaseconnection.JdbcUtil;

public class AddProductToCatalogAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AddProductToCatalogForm product = (AddProductToCatalogForm) form;
		Catalog c = (Catalog) request.getSession().getAttribute("catalog");

		if (!product.validator()) {
			String q = "insert into product values('" + product.getId() + "','" + c.getCatalogid() + "','"
					+ product.getName() + "','" + product.getDesc() + "','" + product.getCat() + "','"
					+ product.getQty() + "','" + product.getWt() + "')";
			JdbcUtil.updtQuery(q);
			product.clearFormValues();
		}
		return (mapping.findForward("success"));
	}
}