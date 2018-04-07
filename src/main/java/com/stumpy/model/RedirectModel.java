package com.stumpy.model;

import java.io.Serializable;

public class RedirectModel implements Serializable {

	private static final long serialVersionUID = 6012607193836897563L;

	private String longUrl;

	/**
	 * Constructor using all fields.
	 * 
	 * @param longUrl.
	 */
	public RedirectModel(String longUrl) {
		super();
		this.longUrl = longUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

}
