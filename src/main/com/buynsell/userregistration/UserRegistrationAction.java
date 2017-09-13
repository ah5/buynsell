package com.buynsell.userregistration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.buynsell.businessobjects.ResponseMessage;
import com.buynsell.businessobjects.Users;
import com.buynsell.databaseconnection.JdbcData;
import com.buynsell.databaseconnection.JdbcUtil;

public class UserRegistrationAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResponseMessage m = new ResponseMessage();
		UserRegistrationForm register = (UserRegistrationForm) form;

		String firstname = register.getFirstname();
		String lastname = register.getLastname();
		String companyname = register.getCompanyname();
		String emailid = register.getEmailid();
		String dateofbirth = register.getDateofbirth();
		String address = register.getAddress();
		String shippingaddress = register.getShippingaddress();
		String country = register.getCountry();
		String city = register.getCity();
		String state = register.getState();
		String pin = register.getPin();
		String phone = register.getPhone();
		String fax = register.getFax();
		String userid = register.getUserid();
		String p1 = register.getP1();
		String hintques = register.getHintques();
		String hintans = register.getHintans();

		if (register.validator()) {
			UserRegistrationForm.errorExists = false;
			m.setType("Failure");
			m.setHeading("Registration Failure");
			m.setContent(
					"Either of the following errors might have occured :<br><li>First Name is Required !<br><li>A proper Email id Needed for sending Transcation informations !<br><li>You might have enterd an invalid email id !<br><li>Date of Birth is required !<br><li>Address is a must, it has to be Entered !<br><li>Shipping Address is also must ! It can be the same as your Address !<br><li>Please select your Country !<br><li>City is Compulsory !<br><li>Pin-Code or Zip-Code should be entered !<br><li>Phone Number is Mandatory !<br><li>Please do enter the Account User Id of your choice ! <br><li>User Id might already Exist !<br><li>You have to enter the same Password in both the password and the password confirmation field !");
			m.setFooting("Sorry ! Do Try Once Again !");
		} else {
			UserRegistrationForm.errorExists = false;
			Users user = new Users();
			user = JdbcData.loadUser(userid);
			if (user == null) {
				String query = "insert into users values ('" + userid + "' ,'" + firstname + "','" + lastname + "','"
						+ companyname + "','" + emailid + "','" + p1 + "','" + hintques + "','" + hintans + "','"
						+ dateofbirth + "','" + address + "','" + shippingaddress + "','" + country + "','" + city
						+ "','" + state + "','" + pin + "','" + phone + "','" + fax + "')";
				JdbcUtil.updtQuery(query);
				m.setType("Success");
				m.setHeading("Congratulations");
				m.setContent(
						"You have successfully Registered as an user of BUY N SELL. <br> Now you are entitled to avail all the services within <br> BUY N SELL. Go on and Login to perform further activities over here !<br>");
				m.setFooting("Thank You !");
			} else {
				m.setType("Failure");
				m.setHeading("Registration Failure");
				m.setContent(
						"Either of the following errors might have occured :<br><li>First Name is Required !<br><li>A proper Email id Needed for sending Transcation informations !<br><li>Date of Birth is required !<br><li>Address is a must, it has to be Entered !<br><li>Shipping Address is also must ! It can be the same as your Address !<br><li>Please select your Country !<br><li>City is Compulsory !<br><li>Pin-Code or Zip-Code should be entered !<br><li>Phone Number is Mandatory !<br><li>Please do enter the Account User Id of your choice ! <br><li>User Id might already Exist !<br><li>You have to enter the same Password in both the password and the password confirmation field !");
				m.setFooting("Sorry ! Do Try Once Again !");
			}
		}
		request.getSession().setAttribute("response", m);
		return (mapping.findForward("response"));
	}
}
