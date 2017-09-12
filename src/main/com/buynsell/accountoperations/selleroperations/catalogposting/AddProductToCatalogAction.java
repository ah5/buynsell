package com.buynsell.accountoperations.selleroperations.catalogposting;

import com.buynsell.databaseconnection.*;
import com.buynsell.businessobjects.*;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class AddProductToCatalogAction extends Action
{
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response)
								throws Exception
	{
		AddProductToCatalogForm product=(AddProductToCatalogForm) form;
		Catalog c	=	(Catalog) request.getSession().getAttribute("catalog");
		
		if(! product.validator())
		{
			String q="insert into product values('"+product.getId()+"','"+c.getCatalogid()+"','"+product.getName()+"','"+product.getDesc()+"','"+product.getCat()+"','"+product.getQty()+"','"+product.getWt()+"')";
			JdbcUtil.updtQuery(q);
			product.clearFormValues();
		}
		return (mapping.findForward ("success"));	
	}
}