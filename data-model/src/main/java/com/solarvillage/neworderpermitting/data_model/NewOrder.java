package com.solarvillage.neworderpermitting.data_model;

import java.io.Serializable;

public class NewOrder implements Serializable {

	private final static long serialVersionUID = 1L;

	private String name;
	private String installationType;
	private String hoaMember;
	private String address;
	private Integer area;
	private Integer price;
	private boolean eletricalPermitResult;
	private boolean structuralPermitResult;
	private boolean compensation = false;

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

	public String getHoaMember() {
		return hoaMember;
	}

	public void setHoaMember(String hoaMember) {
		this.hoaMember = hoaMember;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public boolean isEletricalPermitResult() {
		return eletricalPermitResult;
	}

	public void setEletricalPermitResult(boolean eletricalPermitResult) {
		this.eletricalPermitResult = eletricalPermitResult;
	}

	public boolean isStructuralPermitResult() {
		return structuralPermitResult;
	}

	public void setStructuralPermitResult(boolean structuralPermitResult) {
		this.structuralPermitResult = structuralPermitResult;
	}

	public boolean isCompensation() {
		return compensation;
	}

	public void setCompensation(boolean compensation) {
		this.compensation = compensation;
	}

	@Override
	public String toString() {
		return "NewOrder [name=" + name + ", installationType="
				+ installationType + ", hoaMember=" + hoaMember + ", address="
				+ address + ", area=" + area + ", price=" + price
				+ ", eletricalPermitResult=" + eletricalPermitResult
				+ ", structuralPermitResult=" + structuralPermitResult
				+ ", compensation=" + compensation + "]";
	}


}
