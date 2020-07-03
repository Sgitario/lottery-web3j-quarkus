package org.sgitario.quarkus;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/owner")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OwnerResource {

	@Inject
	private LotteryService service;

	@POST
	@Path("/{owner}/deploy")
	public String deploy(@PathParam("owner") String owner) throws Exception {
		return service.deployLottery(owner);
	}

	@GET
	@Path("/{owner}/balance")
	public BigInteger getBalance(@PathParam("owner") String owner) throws Exception {
		return service.getBalance(owner);
	}

	@GET
	@Path("/{owner}/players")
	public List<String> getPlayers(@PathParam("owner") String owner) throws Exception {
		return service.getPlayers(owner);
	}

	@GET
	@Path("/{owner}/pickWinner")
	public void pickWinner(@PathParam("owner") String owner) throws Exception {
		service.pickWinner(owner);
	}
}