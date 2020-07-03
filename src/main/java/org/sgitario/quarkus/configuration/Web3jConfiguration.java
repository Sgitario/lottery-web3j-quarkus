package org.sgitario.quarkus.configuration;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Dependent
public class Web3jConfiguration {

	@Produces
	public Web3j web3j(@ConfigProperty(name = "web3j.client-address") String clientAddress) {
		return Web3j.build(new HttpService(clientAddress));
	}
}
