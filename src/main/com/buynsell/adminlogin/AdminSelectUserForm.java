package com.buynsell.adminlogin;

import org.apache.struts.action.ActionForm;

public class AdminSelectUserForm extends ActionForm {
	String userSelected = null;

	public String getUserSelected() {
		return this.userSelected;
	}

	public void setUserSelected(String userSelected) {
		this.userSelected = userSelected;
	}

	public void reset() {
		setUserSelected(null);
	}

	public boolean validator() {
		if ((getUserSelected() == null) || (getUserSelected().length() < 1))
			return true;
		else
			return false;
	}
}
