package com.buynsell.accountoperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.ResponseMessage;
import com.buynsell.databaseconnection.JdbcUtil;

public class CreditCardEditAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CreditCardEditForm ccform = (CreditCardEditForm) form;

		String yr = null;
		String mo = ccform.getExpmonth();
		if (Integer.parseInt(ccform.getExpyear()) >= 0 && Integer.parseInt(ccform.getExpyear()) <= 20)
			yr = "20" + ccform.getExpyear();
		else if (Integer.parseInt(ccform.getExpyear()) >= 97 && Integer.parseInt(ccform.getExpyear()) <= 99)
			yr = "19" + ccform.getExpyear();
		String da = null;
		switch (Integer.parseInt(ccform.getExpmonth())) {
		case 1:
			da = "31";
			break;
		case 2:
			da = "28";
			break;
		case 3:
			da = "31";
			break;
		case 4:
			da = "30";
			break;
		case 5:
			da = "31";
			break;
		case 6:
			da = "30";
			break;
		case 7:
			da = "31";
			break;
		case 8:
			da = "31";
			break;
		case 9:
			da = "30";
			break;
		case 10:
			da = "31";
			break;
		case 11:
			da = "30";
			break;
		case 12:
			da = "31";
			break;
		}
		String d = yr + "-" + mo + "-" + da;
		String sql = "update creditcarddetails set CCNumber='" + ccform.getCcnumber() + "', " + "CCType='"
				+ ccform.getCctype() + "', " + "ExpiryDate='" + d + "' where UserID like '" + ccform.getUserid() + "'";
		JdbcUtil.updtQuery(sql);

		ResponseMessage m = new ResponseMessage();
		m.setType("Success");
		m.setHeading("Credit Card Details Editing Successful");
		m.setContent("All your new credit card details have been furnished in our database");
		m.setFooting("Thank You!");
		request.getSession().setAttribute("response", m);
		ccform.reset();
		return (mapping.findForward("response"));
	}
}