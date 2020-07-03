package org.sgitario.quarkus;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
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

@ApplicationScoped
public class LotteryService {

	private static final Logger LOGGER = Logger.getLogger(LotteryService.class);

	@Inject
	private Web3j web3j;
	@Inject
	private LotteryProperties config;

	private Map<String, String> lotteryByOwner = new ConcurrentHashMap<>();

	public BigDecimal getBalance(String owner) throws IOException {
		BigInteger wei = web3j.ethGetBalance(lotteryByOwner.get(owner), DefaultBlockParameterName.LATEST).send()
				.getBalance();
		return Convert.fromWei(wei.toString(), Unit.ETHER);
	}

	public void join(String owner, String account, BigDecimal ethers) throws Exception {
		Lottery lottery = loadContract(owner, account);
		TransactionReceipt tx = lottery.enter(Convert.toWei(ethers, Unit.ETHER).toBigInteger()).send();
		tx.getLogs().forEach(LOGGER::info);
	}

	public String deployLottery(String owner) throws Exception {
		LOGGER.info("Deploying Lottery...");
		Lottery contract = Lottery.deploy(web3j, txManager(owner), config.gas()).send();
		LOGGER.info("Deployed new contract with address: " + contract.getContractAddress());
		lotteryByOwner.put(owner, contract.getContractAddress());
		return contract.getContractAddress();
	}

	@SuppressWarnings("unchecked")
	public List<String> getPlayers(String owner) throws Exception {
		Lottery lottery = loadContractFromOwner(owner);
		List<String> players = lottery.getPlayers().send();
		return players;
	}

	public String pickWinner(String owner) throws Exception {
		Lottery lottery = loadContractFromOwner(owner);
		lottery.pickWinner().send();
		lotteryByOwner.remove(owner);
		return lottery.getWinner().send();
	}

	private Lottery loadContractFromOwner(String owner) {
		return loadContract(owner, owner);
	}

	private Lottery loadContract(String owner, String accountAddress) {
		return Lottery.load(lotteryByOwner.get(owner), web3j, txManager(accountAddress), config.gas());
	}

	private TransactionManager txManager(String address) {
		return new ClientTransactionManager(web3j, address);
	}
}
