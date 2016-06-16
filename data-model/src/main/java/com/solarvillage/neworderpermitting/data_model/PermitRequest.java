package com.solarvillage.neworderpermitting.data_model;

import java.io.Serializable;

public class PermitRequest implements Serializable {

	private final static long serialVersionUID = 2L;

	private String name;
	private String address;
	private boolean approved;

	public PermitRequest() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "PermitRequest [name=" + name + ", address=" + address
				+ ", approved=" + approved + "]";
	}

}