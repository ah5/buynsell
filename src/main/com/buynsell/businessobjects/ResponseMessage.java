package com.buynsell.businessobjects;

public class ResponseMessage {
	protected String type;
	protected String heading;
	protected String content;
	protected String footing;

	public ResponseMessage() {
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHeading() {
		return this.heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFooting() {
		return this.footing;
	}

	public void setFooting(String footing) {
		this.footing = footing;
	}
}
