package org.sgitario.quarkus.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.11.
 */
@SuppressWarnings("rawtypes")
public class Lottery extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055610531806100326000396000f3fe60806040526004361061004a5760003560e01c8063481c6a751461004f5780635d495aea146100805780638b5b9ccc146100975780638e7ea5b2146100fc578063e97dcb6214610111575b600080fd5b34801561005b57600080fd5b50610064610119565b604080516001600160a01b039092168252519081900360200190f35b34801561008c57600080fd5b50610095610128565b005b3480156100a357600080fd5b506100ac61022e565b60408051602080825283518183015283519192839290830191858101910280838360005b838110156100e85781810151838201526020016100d0565b505050509050019250505060405180910390f35b34801561010857600080fd5b506100646102f4565b610095610367565b6000546001600160a01b031681565b6000546001600160a01b0316331461018a5760408051600160e51b62461bcd02815260206004820181905260248201527f4f6e6c79204d616e616765722063616e207069636b207468652077696e6e6572604482015290519081900360640190fd5b600154600090610198610408565b8161019f57fe5b069050600181815481106101af57fe5b6000918252602082200154600280546001600160a01b0319166001600160a01b03928316179081905560405130939190921691833180156108fc0292909190818181858888f1935050505015801561020b573d6000803e3d6000fd5b5060408051600081526020810191829052516102299160019161047c565b505050565b6000546060906001600160a01b031633146102935760408051600160e51b62461bcd02815260206004820181905260248201527f4f6e6c79204d616e616765722063616e207069636b207468652077696e6e6572604482015290519081900360640190fd5b60018054806020026020016040519081016040528092919081815260200182805480156102e957602002820191906000526020600020905b81546001600160a01b031681526001909101906020018083116102cb575b505050505090505b90565b600080546001600160a01b031633146103575760408051600160e51b62461bcd02815260206004820181905260248201527f4f6e6c79204d616e616765722063616e207069636b207468652077696e6e6572604482015290519081900360640190fd5b506002546001600160a01b031690565b662386f26fc1000034116103c55760408051600160e51b62461bcd02815260206004820152601d60248201527f4d7573742068617665206174206c6561737420302e3031206574686572000000604482015290519081900360640190fd5b6001805480820182556000919091527fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf60180546001600160a01b03191633179055565b60004442600160405160200180848152602001838152602001828054801561045957602002820191906000526020600020905b81546001600160a01b0316815260019091019060200180831161043b575b505060408051601f19818403018152919052805160209091012094505050505090565b8280548282559060005260206000209081019282156104d1579160200282015b828111156104d157825182546001600160a01b0319166001600160a01b0390911617825560209092019160019091019061049c565b506104dd9291506104e1565b5090565b6102f191905b808211156104dd5780546001600160a01b03191681556001016104e756fea165627a7a72305820801d6cda6d3612d8ecd3c7096150faf438b26d7fa38e9f4f0e782500eb100bb20029";

    public static final String FUNC_MANAGER = "manager";

    public static final String FUNC_PICKWINNER = "pickWinner";

    public static final String FUNC_GETPLAYERS = "getPlayers";

    public static final String FUNC_GETWINNER = "getWinner";

    public static final String FUNC_ENTER = "enter";

    @Deprecated
    protected Lottery(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Lottery(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Lottery(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Lottery(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> manager() {
        final Function function = new Function(FUNC_MANAGER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pickWinner() {
        final Function function = new Function(
                FUNC_PICKWINNER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getPlayers() {
        final Function function = new Function(FUNC_GETPLAYERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<String> getWinner() {
        final Function function = new Function(FUNC_GETWINNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> enter(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_ENTER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    @Deprecated
    public static Lottery load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Lottery(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Lottery load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Lottery(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Lottery load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Lottery(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Lottery load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Lottery(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Lottery> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Lottery.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Lottery> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Lottery.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Lottery> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Lottery.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Lottery> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Lottery.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
