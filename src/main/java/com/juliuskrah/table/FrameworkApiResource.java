package com.juliuskrah.table;

import static org.springframework.http.HttpStatus.OK;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FrameworkApiResource {
	private static final Logger log = LoggerFactory.getLogger(FrameworkApiResource.class);
	private final FrameworkRepository repository;
	private final FrameworkSearchRepository searchRepository;

	public FrameworkApiResource(FrameworkRepository repository, FrameworkSearchRepository searchRepository) {
		this.repository = repository;
		this.searchRepository = searchRepository;
	}

	@GetMapping("/frameworks")
	public ResponseEntity<List<Framework>> frameworks(@PageableDefault(size = 5) Pageable pageable) {
		log.info("REST request to get a page of Frameworks");
		Page<Framework> page = repository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/frameworks");
		return new ResponseEntity<>(page.getContent(), headers, OK);
	}

	@GetMapping("/_search/frameworks")
	public ResponseEntity<List<Framework>> searchStaff(@RequestParam String query, @PageableDefault(size = 5) Pageable pageable) {
		log.info("REST request to search for a page of Frameworks for query {}", query);
		Page<Framework> page = searchRepository.search(queryStringQuery(query), pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page,
				"/api/_search/frameworks");
		return new ResponseEntity<>(page.getContent(), headers, OK);
	}
}
