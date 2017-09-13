package com.buynsell.userregistration;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UserRegistrationForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3041367419037323633L;
	String firstname = null;
	String lastname = null;
	String companyname = null;
	String emailid = null;
	String dateofbirth = null;
	String address = null;
	String shippingaddress = null;
	String country = null;
	String city = null;
	String state = null;
	String pin = null;
	String phone = null;
	String fax = null;
	String userid = null;
	String p1 = null;
	String p2 = null;
	String hintques = null;
	String hintans = null;
	static boolean errorExists = false;

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCompanyname() {
		return this.companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShippingaddress() {
		return this.shippingaddress;
	}

	public void setShippingaddress(String shippingaddress) {
		this.shippingaddress = shippingaddress;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getP1() {
		return this.p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return this.p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getHintques() {
		return this.hintques;
	}

	public void setHintques(String hintques) {
		this.hintques = hintques;
	}

	public String getHintans() {
		return this.hintans;
	}

	public void setHintans(String hintans) {
		this.hintans = hintans;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.firstname = null;
		this.lastname = null;
		this.companyname = null;
		this.emailid = null;
		this.dateofbirth = null;
		this.address = null;
		this.shippingaddress = null;
		this.country = null;
		this.city = null;
		this.state = null;
		this.pin = null;
		this.phone = null;
		this.fax = null;
		this.userid = null;
		this.p1 = null;
		this.p2 = null;
		this.hintques = null;
		this.hintans = null;
	}

	public boolean validator() {
		char[] chars;
		if ((firstname == null) || (firstname.length() < 1))
			errorExists = true;

		if ((emailid == null) || (emailid.length() < 1))
			errorExists = true;
		if (!emailValidator(emailid))
			errorExists = true;

		if ((dateofbirth == null) || (dateofbirth.length() < 1))
			errorExists = true;
		if ((address == null) || (address.length() < 1))
			errorExists = true;
		if ((shippingaddress == null) || (shippingaddress.length() < 1))
			errorExists = true;
		if ((country == null) || (country.length() < 1))
			errorExists = true;
		if ((city == null) || (city.length() < 1))
			errorExists = true;

		if ((pin == null))
			errorExists = true;
		else {
			chars = pin.toCharArray();
			for (int i = 0; i < chars.length; i++)
				if (!Character.isDigit(chars[i]))
					errorExists = true;
		}

		if ((phone == null))
			errorExists = true;
		else {
			chars = phone.toCharArray();
			for (int i = 0; i < chars.length; i++)
				if (!Character.isDigit(chars[i]))
					errorExists = true;
		}

		if (fax != null) {
			chars = fax.toCharArray();
			for (int i = 0; i < chars.length; i++)
				if (!Character.isDigit(chars[i]))
					errorExists = true;
		}

		if ((userid == null) || (userid.length() < 1))
			errorExists = true;
		if ((p1 == null) || (p1.length() < 1) || (p2 == null) || (p2.length() < 1) || (!p1.equals(p2)))
			errorExists = true;
		return errorExists;
	}

	@SuppressWarnings("deprecation")
	public boolean emailValidator(String email) {
		boolean valid = true;
		char[] chars;
		String[] tokensMain = email.split("@");
		if (tokensMain.length == 1)
			valid = false;
		else {
			chars = tokensMain[1].toCharArray();
			for (int i = 0; i < chars.length; i++)
				if ((chars[i] == '.') && (i > 0))
					valid = true;
			chars = email.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if (((Character.isSpace(chars[i])) || !(Character.isLetterOrDigit(chars[i])))) {
					valid = false;
					break;
				}
			}
			for (int i = 0; i < chars.length; i++) {
				if (((Character.isSpace(chars[i])) || !(Character.isLetterOrDigit(chars[i])))) {
					if ((i <= tokensMain[0].length()) && (!(chars[i] == '_') || (chars[i] == '.'))) {
						valid = true;
						break;
					}
				}
			}
		}
		return valid;
	}
}