package org.sgitario.quarkus.web3j.client.deployment;

import org.sgitario.quarkus.web3j.client.runtime.Web3jClientProducer;
import org.sgitario.quarkus.web3j.client.runtime.Web3jConfiguration;
import org.sgitario.quarkus.web3j.client.runtime.Web3jRecorder;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanContainerBuildItem;
import io.quarkus.arc.deployment.BeanContainerListenerBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class QuarkusWeb3jClientProcessor {

	private static final String FEATURE = "web3j-client";

	@BuildStep
	void build(BuildProducer<FeatureBuildItem> featureProducer,
			BuildProducer<AdditionalBeanBuildItem> additionalBeanProducer,
			BuildProducer<BeanContainerListenerBuildItem> containerListenerProducer) {

		featureProducer.produce(new FeatureBuildItem(FEATURE));

		additionalBeanProducer.produce(AdditionalBeanBuildItem.unremovableOf(Web3jClientProducer.class));
	}

	@BuildStep
	@Record(ExecutionTime.RUNTIME_INIT)
	void configureProducer(Web3jRecorder recorder, BeanContainerBuildItem beanContainerBuildItem,
			Web3jConfiguration configuration) {
		recorder.update(beanContainerBuildItem.getValue(), configuration);
	}

}
