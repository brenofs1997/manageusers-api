package com.api.manageusers.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.api.manageusers.models.UserModel;

public class AdressDto {

	@NotEmpty(message = "Public Place may not be empty")
	private String publicPlace;

	@NotEmpty(message = "CEP may not be empty")
	private String CEP;
	
	@NotEmpty(message = "number may not be empty")
	private String number;
	
	@NotEmpty(message = "city may not be empty")
	private String city;
	
	@NotNull
	private Boolean principal;

	@NotNull
	private UserModel userModel;
	
	public String getPublicPlace() {
		return publicPlace;
	}

	public void setPublicPlace(String publicPlace) {
		this.publicPlace = publicPlace;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String CEP) {
		this.CEP = CEP;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	
		
}
