package org.sgitario.quarkus;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sgitario.quarkus.utils.GanacheTestResource;
import org.web3j.protocol.Web3j;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
@QuarkusTestResource(GanacheTestResource.class)
public class OwnerResourceTest {

	private static final String OWNER_PATH = "/owner/%s";

	@ConfigProperty(name = GanacheTestResource.URL_PROPERTY)
	private String nodeUrl;

	@Inject
	private Web3j web3j;

	private String owner;

	@BeforeEach
	public void setup() throws IOException {
		owner = web3j.ethAccounts().send().getAccounts().get(0);
	}

	@Test
	public void testDeploy() {
		given().contentType(ContentType.JSON).when().post(ownerPath() + "/deploy").then().statusCode(200);
	}

	private String ownerPath() {
		return String.format(OWNER_PATH, owner);
	}

}