package org.sgitario.quarkus;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;
import org.sgitario.quarkus.model.Account;
import org.sgitario.quarkus.model.Quantity;

@Path("/lottery")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tags(value = @Tag(name = "lottery", description = "All the Lottery methods"))
public class OwnerResource {

	@Inject
	LotteryService service;

	@POST
	@Path("/{owner}")
	@Operation(summary = "Deploy a new Lottery ")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Return the lottery account") })
	public Account deploy(@Parameter(description = "Owner account", required = true) @PathParam("owner") String owner)
			throws Exception {
		Account account = new Account();
		account.setAccount(service.deployLottery(owner));
		return account;
	}

	@GET
	@Path("/{owner}/balance")
	@Operation(summary = "Get the balance of the Lottery contract")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Return the lottery balance") })
	public Quantity getBalance(
			@Parameter(description = "Owner account", required = true) @PathParam("owner") String owner)
			throws Exception {
		Quantity quantity = new Quantity();
		quantity.setEthers(service.getBalance(owner));
		return quantity;
	}

	@POST
	@Path("/{owner}/finish")
	@Operation(summary = "Finish the Lottery and pick a winner")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Return the winner account", content = @Content(schema = @Schema(implementation = Account.class))) })
	public Account pickWinner(
			@Parameter(description = "Owner account", required = true) @PathParam("owner") String owner)
			throws Exception {
		Account winner = new Account();
		winner.setAccount(service.pickWinner(owner));
		return winner;
	}

	@GET
	@Path("/{owner}/players")
	@Operation(summary = "Return all the players in the lottery")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Return the list of players") })
	public List<String> getPlayers(
			@Parameter(description = "Owner account", required = true) @PathParam("owner") String owner)
			throws Exception {
		return service.getPlayers(owner);
	}

	@POST
	@Path("/{owner}/players/{account}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(summary = "Join a new player in the lottery")
	public void joinLottery(@Parameter(description = "Owner account", required = true) @PathParam("owner") String owner,
			@Parameter(description = "Player account", required = true) @PathParam("account") String account,
			@Parameter(description = "Quantity", required = true) Quantity quantity) throws Exception {
		service.join(owner, account, quantity.getEthers());
	}
}