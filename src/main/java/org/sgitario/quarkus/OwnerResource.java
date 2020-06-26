package org.sgitario.quarkus;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/owner")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OwnerResource {

	@Inject
	private LotteryService service;

	@GET
	@Path("/balance")
	public BigInteger getBalance() throws IOException {
		return service.getBalance();
	}

	@GET
	@Path("/players")
	public List<String> getPlayers() throws Exception {
		return service.getPlayers();
	}

	@GET
	@Path("/pickWinner")
	public void pickWinner() throws Exception {
		service.pickWinner();
	}
}