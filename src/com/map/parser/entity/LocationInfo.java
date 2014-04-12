package com.map.parser.entity;

public class LocationInfo {

	private boolean status;
	private String location;
	private String longitude;
	private String latitude; 
	private String shortname;
	private String type;
	private String longname;
	private String address;
	
	
	public LocationInfo(boolean status, String location, String longitude,
			String latitude, String shortname, String type, String longname,
			String address) {
		super();
		this.status = status;
		this.location = location;
		this.longitude = longitude;
		this.latitude = latitude;
		this.shortname = shortname;
		this.type = type;
		this.longname = longname;
		this.address = address;
	}
	
	public boolean isStatus() {
		return status;
	}
	public String getLocation() {
		return location;
	}
	public String getLongitude() {
		return longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public String getShortname() {
		return shortname;
	}
	public String getType() {
		return type;
	}
	public String getLongname() {
		return longname;
	}
	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "LocationInfo [status=" + status + ", location=" + location
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", shortname=" + shortname + ", type=" + type + ", longname="
				+ longname + ", address=" + address + "]";
	}
	
	
	
	
	

}
