package org.sgitario.quarkus.utils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class GanacheTestResource implements QuarkusTestResourceLifecycleManager {

	public static final String URL_PROPERTY = "quarkus.web3j.url";
	private static final int PORT = 8545;
	private static final Logger LOGGER = LoggerFactory.getLogger(GanacheTestResource.class);

	private final GenericContainer<?> resource;

	@SuppressWarnings("resource")
	public GanacheTestResource() {
		resource = new GenericContainer<>("trufflesuite/ganache-cli:v6.10.0-beta.2").withExposedPorts(PORT)
				.withCommand("--accounts 10").withLogConsumer(new Slf4jLogConsumer(LOGGER))
				.waitingFor(Wait.forLogMessage(".*Listening on 0.0.0.0.*", 1));
	}

	@Override
	public Map<String, String> start() {
		resource.start();
		return Collections.singletonMap(URL_PROPERTY, getNodeUrl());
	}

	@Override
	public void stop() {
		resource.stop();
	}

	@Override
	public void inject(Object testInstance) {
		Class<?> c = testInstance.getClass();
		while (c != Object.class) {
			for (Field f : c.getDeclaredFields()) {
				ConfigProperty configProperty = f.getAnnotation(ConfigProperty.class);
				if (configProperty != null && URL_PROPERTY.equals(configProperty.name())) {
					f.setAccessible(true);
					try {
						f.set(testInstance, getNodeUrl());
						return;
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
			c = c.getSuperclass();
		}
	}

	private String getNodeUrl() {
		return String.format("http://localhost:%s", resource.getMappedPort(PORT));
	}

}
