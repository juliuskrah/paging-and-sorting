package com.juliuskrah.table;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@SpringBootApplication
@EnableJpaRepositories(includeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, value = JpaRepository.class))
@EnableElasticsearchRepositories(includeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, value = ElasticsearchRepository.class))
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
		Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
		factory.setResources(new Resource[] { new ClassPathResource("frameworks.json") });
		return factory;
	}
}
