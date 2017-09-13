package com.buynsell.itemsearch;

import org.apache.struts.action.ActionForm;

public class ItemSearchForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3885606281335386792L;
	String itemName = null;
	String itemCategory = null;

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCategory() {
		return this.itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public void reset() {
		this.setItemName(null);
		this.setItemCategory(null);
	}

	public boolean validator() {
		boolean temp = false;
		if ((itemName == null) || (itemName.length() < 1))
			temp = true;
		if ((itemCategory == null) || (itemCategory.length() < 1))
			temp = true;
		return temp;
	}
}