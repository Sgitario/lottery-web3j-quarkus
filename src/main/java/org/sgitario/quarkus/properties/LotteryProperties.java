package org.sgitario.quarkus.properties;

import java.beans.Transient;
import java.math.BigInteger;

import org.web3j.tx.gas.StaticGasProvider;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "lottery.contract")
public class LotteryProperties {
	private BigInteger gasPrice;
	private BigInteger gasLimit;
	private String ownerAddress;
	private String contractAddress;

	public BigInteger getGasLimit() {
		return gasLimit;
	}

	public void setGasLimit(BigInteger gasLimit) {
		this.gasLimit = gasLimit;
	}

	public BigInteger getGasPrice() {
		return gasPrice;
	}

	public void setGasPrice(BigInteger gasPrice) {
		this.gasPrice = gasPrice;
	}

	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	@Transient
	public StaticGasProvider gas() {
		return new StaticGasProvider(gasPrice, gasLimit);
	}
}
