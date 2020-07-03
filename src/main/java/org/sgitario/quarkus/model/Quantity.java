package org.sgitario.quarkus.model;

import java.math.BigDecimal;

public class Quantity {
	private BigDecimal ethers;

	public BigDecimal getEthers() {
		return ethers;
	}

	public void setEthers(BigDecimal ethers) {
		this.ethers = ethers;
	}
}
