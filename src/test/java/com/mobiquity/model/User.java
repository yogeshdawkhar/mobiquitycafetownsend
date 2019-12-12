package com.mobiquity.model;

import com.mobiquity.util.UtililtyFunctions;

public class User {

	private String firstName;
	private String lastName;
	private String startDate;
	private String emailId;

	public User() {
		this.firstName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(5);
		this.lastName = UtililtyFunctions.generateRandomStringWithAlphabetesOnly(5);
		this.startDate = UtililtyFunctions.getCurrentDate();
		this.emailId = UtililtyFunctions.generateRandomEmail(9);
	}

	public User(String firstName, String lastName, String startDate, String emailId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.startDate = startDate;
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
