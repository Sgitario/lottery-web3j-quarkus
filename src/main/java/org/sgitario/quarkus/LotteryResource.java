package org.sgitario.quarkus;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.sgitario.quarkus.model.Player;

@Path("/player")
public class LotteryResource {
	@Inject
	private LotteryService service;

	@POST
	@Path("/join/{owner}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void joinLottery(@PathParam("owner") String owner, Player player) throws Exception {
		service.join(owner, player.getAddress(), player.getEthers());
	}
}