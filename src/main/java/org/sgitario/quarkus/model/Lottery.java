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
    public static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b0319163317905561048d806100326000396000f3fe60806040526004361061003f5760003560e01c8063481c6a75146100445780635d495aea146100755780638b5b9ccc1461008c578063e97dcb62146100f1575b600080fd5b34801561005057600080fd5b506100596100f9565b604080516001600160a01b039092168252519081900360200190f35b34801561008157600080fd5b5061008a610108565b005b34801561009857600080fd5b506100a16101fd565b60408051602080825283518183015283519192839290830191858101910280838360005b838110156100dd5781810151838201526020016100c5565b505050509050019250505060405180910390f35b61008a6102c3565b6000546001600160a01b031681565b6000546001600160a01b0316331461016a5760408051600160e51b62461bcd02815260206004820181905260248201527f4f6e6c79204d616e616765722063616e207069636b207468652077696e6e6572604482015290519081900360640190fd5b600154600090610178610364565b8161017f57fe5b06905060006001828154811061019157fe5b60009182526020822001546040516001600160a01b03909116925030918391833180156108fc0292909190818181858888f193505050501580156101d9573d6000803e3d6000fd5b5060408051600081526020810191829052516101f7916001916103d8565b50505050565b6000546060906001600160a01b031633146102625760408051600160e51b62461bcd02815260206004820181905260248201527f4f6e6c79204d616e616765722063616e207069636b207468652077696e6e6572604482015290519081900360640190fd5b60018054806020026020016040519081016040528092919081815260200182805480156102b857602002820191906000526020600020905b81546001600160a01b0316815260019091019060200180831161029a575b505050505090505b90565b662386f26fc1000034116103215760408051600160e51b62461bcd02815260206004820152601d60248201527f4d7573742068617665206174206c6561737420302e3031206574686572000000604482015290519081900360640190fd5b6001805480820182556000919091527fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf60180546001600160a01b03191633179055565b6000444260016040516020018084815260200183815260200182805480156103b557602002820191906000526020600020905b81546001600160a01b03168152600190910190602001808311610397575b505060408051601f19818403018152919052805160209091012094505050505090565b82805482825590600052602060002090810192821561042d579160200282015b8281111561042d57825182546001600160a01b0319166001600160a01b039091161782556020909201916001909101906103f8565b5061043992915061043d565b5090565b6102c091905b808211156104395780546001600160a01b031916815560010161044356fea165627a7a7230582046b131bf9364e42fc78d4afe7f1ac2267f658de9aae1b6022c1b872d9286c5c70029";

    public static final String FUNC_MANAGER = "manager";

    public static final String FUNC_PICKWINNER = "pickWinner";

    public static final String FUNC_GETPLAYERS = "getPlayers";

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
