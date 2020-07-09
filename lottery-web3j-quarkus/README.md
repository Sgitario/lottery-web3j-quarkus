```
> docker run -d --name ethereum -p 8545:8545 -p 30303:30303 ethereum/client-go --rpc --rpcaddr "0.0.0.0" --rpcapi="db,eth,net,web3,personal" --rpccorsdomain "*" --dev --allow-insecure-unlock
```

```go
> personal.newAccount('ABC')
"0xf4cffcaed2700b9a4e937036a044ea42ecd95e48"
> personal.unlockAccount("0xf4cffcaed2700b9a4e937036a044ea42ecd95e48", "ABC")
> eth.accounts
["0xb18b4450b90a21b20ec5ed45df5f1a500e0c476a", "0xf4cffcaed2700b9a4e937036a044ea42ecd95e48"]
```

```go
> eth.sendTransaction({from: eth.accounts[0], to: eth.accounts[1], value: web3.toWei(100000, 'ether')})
"0x65fc53055e970c92d294374c269632937d76d399ed6efafa3206e889bd8f9140"
> eth.getBalance(eth.accounts[1])
1.00000000202221601202e+23
```

# Tasks:
- Web3j Extension
- Native mode
- Web
- Write Post

# lottery-web3j-quarkus project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `lottery-web3j-quarkus-0.0.1-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/lottery-web3j-quarkus-0.0.1-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/lottery-web3j-quarkus-0.0.1-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

# Extension

More info in: https://quarkus.io/guides/building-my-first-extension

```
mvn io.quarkus:quarkus-maven-plugin:1.6.0.Final:create-extension -N -DgroupId=org.sgitario.quarkus -DartifactId=quarkus-web3j-client -Dversion=1.0-SNAPSHOT -Dquarkus.nameBase="Web3j Client Extension" 
```
