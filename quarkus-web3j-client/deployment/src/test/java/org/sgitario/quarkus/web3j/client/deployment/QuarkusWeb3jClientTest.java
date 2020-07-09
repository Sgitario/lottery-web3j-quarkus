package org.sgitario.quarkus.web3j.client.deployment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sgitario.quarkus.web3j.client.runtime.Web3jConfiguration;

import io.quarkus.test.QuarkusUnitTest;

public class QuarkusWeb3jClientTest {
	@RegisterExtension
	static final QuarkusUnitTest config = new QuarkusUnitTest()
			.setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class))
			.withConfigurationResource("application.properties");

	@Inject
	Web3jConfiguration web3j;

	@Test
	public void checkUrlIsLoaded() {
		assertEquals(web3j.url, "http://localhost:8545");
	}
}
