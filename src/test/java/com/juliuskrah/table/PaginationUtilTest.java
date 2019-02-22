package com.juliuskrah.table;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PaginationUtilTest {
	private static ObjectMapper objectMapper;

	@BeforeClass
	public static void initialize() {
		objectMapper = new ObjectMapper();
	}

	@Test
	public void testPaginationHeaders() throws JsonParseException, JsonMappingException, IOException {
		Resource classpathResource = new ClassPathResource("frameworks.json");
		List<Framework> frameworks = objectMapper.readValue(classpathResource.getInputStream(), //
				new TypeReference<List<Framework>>() {});
		assertThat(frameworks).hasSize(20);
		
		Page<Framework> page = new PageImpl<>(frameworks, PageRequest.of(1, 5), 20);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "http://example.com/api/frameworks");
		assertThat(headers.getFirst("X-Total-Count")).isEqualTo("20");
		assertThat(headers.getFirst(HttpHeaders.LINK)).contains("http://example.com/api/frameworks?page=0&size=5");
	}
}
