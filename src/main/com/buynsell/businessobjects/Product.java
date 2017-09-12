package com.buynsell.businessobjects;

import java.util.ArrayList;

public class Product {
	protected String productid;
	protected String catalogid;
	protected String name;
	protected String desc;
	protected String cat;
	protected String qty;
	protected String wt;

	public Product() {
	}

	public Product(ArrayList<?> raw) {
		this.productid = (String) raw.get(0);
		this.catalogid = (String) raw.get(1);
		this.name = (String) raw.get(2);
		this.desc = (String) raw.get(3);
		this.cat = (String) raw.get(4);
		this.qty = (String) raw.get(5);
		this.wt = (String) raw.get(6);
	}

	public String getProductid() {
		return this.productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getCatalogid() {
		return this.catalogid;
	}

	public void setCatalogid(String catalogid) {
		this.catalogid = catalogid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCat() {
		return this.cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getQty() {
		return this.qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getWt() {
		return this.wt;
	}

	public void setWt(String wt) {
		this.wt = wt;
	}
}