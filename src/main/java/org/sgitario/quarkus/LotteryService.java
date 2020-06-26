package org.sgitario.quarkus;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.sgitario.quarkus.model.Lottery;
import org.sgitario.quarkus.properties.LotteryProperties;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import io.quarkus.runtime.LaunchMode;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class LotteryService {

	private static final Logger LOGGER = Logger.getLogger(LotteryService.class);

	@Inject
	private Web3j web3j;
	@Inject
	private LotteryProperties config;

	public BigInteger getBalance() throws IOException {
		return web3j.ethGetBalance(config.getContractAddress(), DefaultBlockParameterName.LATEST).send().getBalance();
	}

	public void join(String account, BigDecimal ethers) throws Exception {
		Lottery lottery = loadContract(account);
		TransactionReceipt tx = lottery.enter(Convert.toWei(ethers, Unit.ETHER).toBigInteger()).send();
		tx.getLogs().forEach(LOGGER::info);
	}

	@SuppressWarnings("unchecked")
	public List<String> getPlayers() throws Exception {
		Lottery lottery = loadContract(config.getOwnerAddress());
		return lottery.getPlayers().send();
	}

	public void pickWinner() throws Exception {
		Lottery lottery = loadContract(config.getOwnerAddress());
		lottery.pickWinner().send();
	}

	private Lottery loadContract(String accountAddress) {
		return Lottery.load(config.getContractAddress(), web3j, txManager(accountAddress), config.gas());
	}

	void onStart(@Observes StartupEvent ev) throws Exception {
		if (LaunchMode.current() == LaunchMode.DEVELOPMENT) {
			LOGGER.info("Deploying Lottery...");
			Lottery contract = Lottery.deploy(web3j, txManager(config.getOwnerAddress()), config.gas()).send();
			LOGGER.info("Deployed new contract with address: " + contract.getContractAddress());
			config.setContractAddress(contract.getContractAddress());
		} else if (config.getContractAddress() == null) {
			throw new RuntimeException("Contract Address is mandatory in production mode");
		}
	}

	private TransactionManager txManager(String address) {
		return new ClientTransactionManager(web3j, address);
	}
}
