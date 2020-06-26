package org.sgitario.quarkus.model;

import java.math.BigDecimal;

public class Player {
	private String address;
	private BigDecimal ethers;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getEthers() {
		return ethers;
	}

	public void setEthers(BigDecimal ethers) {
		this.ethers = ethers;
	}
}
