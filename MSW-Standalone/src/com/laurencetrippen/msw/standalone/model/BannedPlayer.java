package com.laurencetrippen.msw.standalone.model;

public class BannedPlayer {
	
	private String uuid;
	private String name;
	private String created;
	private String source;
	private String expires;
	private String reason;
	
	public BannedPlayer(
			String uuid, 
			String name, 
			String created, 
			String source, 
			String expires, 
			String reason) {
		this.uuid = uuid;
		this.name = name;
		this.created = created;
		this.source = source;
		this.expires = expires;
		this.reason = reason;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCreated() {
		return created;
	}
	
	public void setCreated(String created) {
		this.created = created;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getExpires() {
		return expires;
	}
	
	public void setExpires(String expires) {
		this.expires = expires;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}

}