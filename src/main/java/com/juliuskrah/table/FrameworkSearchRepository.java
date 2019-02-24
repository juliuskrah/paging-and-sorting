package com.juliuskrah.table;

import java.util.UUID;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface FrameworkSearchRepository extends ElasticsearchRepository<Framework, UUID>{

}
