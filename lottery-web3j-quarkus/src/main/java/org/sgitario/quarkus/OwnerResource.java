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

import org.sgitario.quarkus.model.Quantity;
import org.sgitario.quarkus.model.Winner;

@Path("/lottery")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OwnerResource {

	@Inject
	LotteryService service;

	@POST
	@Path("/{owner}")
	public String deploy(@PathParam("owner") String owner) throws Exception {
		return service.deployLottery(owner);
	}

	@GET
	@Path("/{owner}/balance")
	public Quantity getBalance(@PathParam("owner") String owner) throws Exception {
		Quantity quantity = new Quantity();
		quantity.setEthers(service.getBalance(owner));
		return quantity;
	}

	@POST
	@Path("/{owner}/finish")
	public Winner pickWinner(@PathParam("owner") String owner) throws Exception {
		Winner winner = new Winner();
		winner.setAccount(service.pickWinner(owner));
		return winner;
	}

	@GET
	@Path("/{owner}/players")
	public List<String> getPlayers(@PathParam("owner") String owner) throws Exception {
		return service.getPlayers(owner);
	}

	@POST
	@Path("/{owner}/players/{account}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void joinLottery(@PathParam("owner") String owner, @PathParam("account") String account, Quantity quantity)
			throws Exception {
		service.join(owner, account, quantity.getEthers());
	}
}