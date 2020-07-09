package org.sgitario.quarkus.web3j.client.runtime;

import io.quarkus.arc.runtime.BeanContainer;
import io.quarkus.arc.runtime.BeanContainerListener;
import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class Web3jRecorder {

	public BeanContainerListener setConfiguration(Web3jConfiguration configuration) {
		return beanContainer -> {
			Web3jClientProducer producer = beanContainer.instance(Web3jClientProducer.class);
			producer.initialize(configuration);
		};

	}

	public void update(BeanContainer beanContainer, Web3jConfiguration configuration) {
		Web3jClientProducer producer = beanContainer.instance(Web3jClientProducer.class);
		producer.initialize(configuration);
	}

}
