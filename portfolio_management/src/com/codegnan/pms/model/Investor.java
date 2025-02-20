package com.codegnan.pms.model;

/**
 * Represents an investor in the system.
 */
public class Investor {
    private int investorId;
    private String name;
    private String email;
    private String phone;
    private String dateOfBirth;

    public Investor(int investorId, String name, String email, String phone, String dateOfBirth) {
        this.investorId = investorId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public Investor(String name, String email, String phone, String dateOfBirth) {
        this(0, name, email, phone, dateOfBirth);
    }

    public int getInvestorId() {
        return investorId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

	@Override
	public String toString() {
		return "Investor [investorId=" + investorId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", dateOfBirth=" + dateOfBirth + "]";
	}
}
