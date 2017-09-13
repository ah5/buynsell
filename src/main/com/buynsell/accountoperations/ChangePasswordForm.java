package com.buynsell.accountoperations;

import org.apache.struts.action.ActionForm;

public class ChangePasswordForm extends ActionForm {
	String old = null;
	String new1 = null;
	String new2 = null;
	static boolean errorExists = false;

	public String getOld() {
		return this.old;
	}

	public void setOld(String old) {
		this.old = old;
	}

	public String getNew1() {
		return this.new1;
	}

	public void setNew1(String new1) {
		this.new1 = new1;
	}

	public String getNew2() {
		return this.new2;
	}

	public void setNew2(String new2) {
		this.new2 = new2;
	}

	public void reset() {
		errorExists = false;
		setOld(null);
		setNew1(null);
		setNew2(null);
	}

	public boolean validator() {
		if ((new1 == null) || (new1.length() < 1))
			errorExists = true;
		if ((new2 == null) || (new2.length() < 1))
			errorExists = true;
		if (!(new1.equals(new2)))
			errorExists = true;
		return errorExists;
	}
}