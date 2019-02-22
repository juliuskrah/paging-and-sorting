package com.juliuskrah.table;

import static org.assertj.core.api.Assertions.assertThat;

import org.jetbrains.annotations.NotNull;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = FrameworkRepositoryTest.Initializer.class)
public class FrameworkRepositoryTest {
	@Autowired
	private FrameworkRepository repository;

	@ClassRule
	public static GenericContainer<?> postgres = new GenericContainer<>("postgres:9.6.8") //
			.withExposedPorts(5432) //
			.withEnv("POSTGRES_PASSWORD", "password") //
			.withEnv("POSTGRES_USER", "postgres");

	static {
		postgres.start();
	}

	@Test
	public void testFindCountAndPageable() {
		long count = repository.count();
		assertThat(count).isEqualTo(20);
		Page<Framework> first5Frameworks = repository.findAll(PageRequest.of(0, 5));
		assertThat(first5Frameworks.getSize()).isEqualTo(5);
	}

	public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		public void initialize(@NotNull ConfigurableApplicationContext configurableApplicationContext) {
			var jdbcUrl = String.format("jdbc:postgresql://%s:%d/%s", postgres.getContainerIpAddress(),
					postgres.getMappedPort(5432), "postgres");
			var values = TestPropertyValues.of( //
					"spring.datasource.hikari.host=" + postgres.getContainerIpAddress(), //
					"spring.datasource.hikari.port=" + postgres.getMappedPort(5432), //
					"spring.datasource.hikari.username=postgres", //
					"spring.datasource.hikari.password=password", //
					"spring.datasource.hikari.url=" + jdbcUrl, //
					"spring.datasource.url=" + jdbcUrl);
			values.applyTo(configurableApplicationContext);
		}
	}
}
