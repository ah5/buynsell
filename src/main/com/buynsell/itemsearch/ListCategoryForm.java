package com.buynsell.itemsearch;

import org.apache.struts.action.ActionForm;

public class ListCategoryForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -885719763694280685L;
	String itemCategory = null;

	public String getItemCategory() {
		return this.itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public void reset() {
		this.setItemCategory(null);
	}
}