package com.solarvillage.neworderpermitting.data_model;

import java.io.Serializable;

public class PermitRequest implements Serializable {

	private final static long serialVersionUID = 2L;

	private String name;
	private String installationType;
	private String address;
	private Integer area;

	public PermitRequest() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstallationType() {
		return installationType;
	}

	public void setInstallationType(String installationType) {
		this.installationType = installationType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "PermitRequest [name=" + name + ", installationType="
				+ installationType + ", address=" + address + ", area=" + area
				+ "]";
	}

}