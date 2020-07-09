package org.sgitario.quarkus.web3j.client.runtime;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@ApplicationScoped
public class Web3jClientProducer {

	private volatile Web3jConfiguration config;

	void initialize(Web3jConfiguration config) {
		this.config = config;
	}

	@Singleton
	@Produces
	public Web3j web3j() {
		return Web3j.build(new HttpService(config.url));
	}
}
