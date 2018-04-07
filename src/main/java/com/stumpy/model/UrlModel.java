package com.stumpy.model;

import java.io.Serializable;

public class UrlModel implements Serializable {

	private static final long serialVersionUID = 8005933239246279968L;

	private Long id;
	private String shortUrl;
	private String longUrl;

	/**
	 * Constructor using all fields.
	 * 
	 * @param id.
	 * @param shortUrl.
	 * @param longUrl.
	 */
	public UrlModel(Long id, String shortUrl, String longUrl) {
		super();
		this.id = id;
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
	}

	public UrlModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

}
