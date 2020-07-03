package org.sgitario.quarkus.properties;

import java.beans.Transient;
import java.math.BigInteger;

import org.web3j.tx.gas.StaticGasProvider;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "lottery.contract")
public class LotteryProperties {
	private BigInteger gasPrice;
	private BigInteger gasLimit;

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

	@Transient
	public StaticGasProvider gas() {
		return new StaticGasProvider(gasPrice, gasLimit);
	}
}
