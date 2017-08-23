package com.solarvillage.neworderpermitting.data_model;

import java.io.Serializable;

public class PermitResponse implements Serializable {

	private final static long serialVersionUID = 2L;
	private static int counter = 1000;

	private String name;
	private String installationType;
	private String address;
	private Integer area;
	private boolean approved;
	private int id;

	public PermitResponse() {
		this.id = ++counter;
	}

	public PermitResponse(PermitRequest request) {
		this.id = ++counter;
		this.name = request.getName();
		this.installationType = request.getInstallationType();
		this.address = request.getAddress();
		this.area = request.getArea();
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

	public String getInstallationType() {
		return installationType;
	}

	public void setInstallationType(String installationType) {
		this.installationType = installationType;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "PermitResponse [name=" + name + ", installationType="
				+ installationType + ", address=" + address + ", area=" + area
				+ ", approved=" + approved + ", id=" + id + "]";
	}

}