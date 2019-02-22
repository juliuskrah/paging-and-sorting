package com.juliuskrah.table;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrameworkApiResource {
	private static final Logger log = LoggerFactory.getLogger(FrameworkApiResource.class);
	private final FrameworkRepository repository;

	public FrameworkApiResource(FrameworkRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/api/frameworks")
	public ResponseEntity<List<Framework>> frameworks(@PageableDefault(size = 5) Pageable pageable) {
		log.info("REST request to get a page of Frameworks");
		Page<Framework> page = repository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/frameworks");
		return new ResponseEntity<>(page.getContent(), headers, OK);
	}
}
