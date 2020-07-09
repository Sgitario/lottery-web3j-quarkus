package org.sgitario.quarkus;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sgitario.quarkus.model.Quantity;
import org.sgitario.quarkus.model.Winner;
import org.sgitario.quarkus.utils.GanacheTestResource;
import org.web3j.protocol.Web3j;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

@QuarkusTest
@QuarkusTestResource(GanacheTestResource.class)
public class OwnerResourceTest {

	private static final String OWNER_PATH = "/lottery/%s";

	@Inject
	Web3j web3j;

	private String owner;
	private Iterator<String> availableAccounts;
	private Response response;

	@BeforeEach
	public void setup() throws IOException {
		RestAssured.defaultParser = Parser.JSON;
		List<String> accounts = web3j.ethAccounts().send().getAccounts();
		owner = accounts.get(0);
		List<String> players = new ArrayList<>(accounts.size() - 1);
		for (int index = 1; index < accounts.size(); index++) {
			players.add(accounts.get(index));
		}

		availableAccounts = players.iterator();
	}

	@Test
	public void testDeploy() {
		whenDeployLottery();
		thenReturnsOk();
	}

	@Test
	public void testJoinPlayers() {
		givenLottery();
		String newPlayer = whenJoinNewPlayer(2.0);
		whenGetPlayers();
		thenPlayerIsFound(newPlayer);
	}

	@Test
	public void testBalance() {
		givenLottery();
		givenPlayerInLotteryWithEther(2.0);
		whenGetBalance();
		thenBalanceIs(2.0);
	}

	@Test
	public void testPickWinner() {
		givenLottery();
		String player = givenPlayerInLotteryWithEther(2.0);
		whenPickWinner();
		thenWinnerIs(player);
	}

	private void givenLottery() {
		whenDeployLottery();
	}

	private String givenPlayerInLotteryWithEther(double ethers) {
		return whenJoinNewPlayer(ethers);
	}

	private void whenPickWinner() {
		response = given().contentType(ContentType.JSON).when().post(ownerPath() + "/finish");
	}

	private void whenGetBalance() {
		response = given().contentType(ContentType.JSON).when().get(ownerPath() + "/balance");
		thenReturnsOk();
	}

	private void whenGetPlayers() {
		response = given().contentType(ContentType.JSON).when().get(ownerPath() + "/players");
		thenReturnsOk();
	}

	private String whenJoinNewPlayer(double ethers) {
		String account = availableAccounts.next();
		Quantity quantity = new Quantity();
		quantity.setEthers(BigDecimal.valueOf(ethers));
		given().contentType(ContentType.JSON).when().body(quantity).post(ownerPath() + "/players/" + account);
		return account;
	}

	private void whenDeployLottery() {
		response = given().contentType(ContentType.JSON).when().post(ownerPath());
	}

	private void thenBalanceIs(double expected) {
		Quantity actual = response.as(Quantity.class);
		assertEquals(expected, actual.getEthers().doubleValue(), "Balance does not match");
	}

	private void thenReturnsOk() {
		response.then().statusCode(200);
	}

	private void thenPlayerIsFound(String player) {
		String[] players = response.as(String[].class);
		assertTrue(Stream.of(players).anyMatch(player::equals), "Player not found");
	}

	private void thenWinnerIs(String expected) {
		Winner actual = response.as(Winner.class);
		assertEquals(expected, actual.getAccount(), "Winner does not match");
	}

	private String ownerPath() {
		return String.format(OWNER_PATH, owner);
	}

}