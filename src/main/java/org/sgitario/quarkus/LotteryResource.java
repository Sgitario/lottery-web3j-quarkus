package org.sgitario.quarkus;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;
import org.sgitario.quarkus.model.Player;

@Path("/player")
public class LotteryResource {

	private static final Logger LOGGER = Logger.getLogger(LotteryService.class);

	@Inject
	private LotteryService service;

	@POST
	@Path("/join")
	@Consumes(MediaType.APPLICATION_JSON)
	public void joinLottery(Player player) throws Exception {
		LOGGER.info("Hello player: " + player);

		service.join(player.getAddress(), player.getEthers());
	}
}