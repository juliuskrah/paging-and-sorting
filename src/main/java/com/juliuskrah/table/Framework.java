package com.juliuskrah.table;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Document(indexName = "framework", type = "framework", shards = 1, replicas = 0, refreshInterval = "-1")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Framework implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	@Id
	@org.springframework.data.annotation.Id
	private UUID id;
	@NotBlank
	private String name;
	@NotBlank
	private String language;
	private String description;
	private boolean web = false;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isWeb() {
		return web;
	}

	public void setWeb(boolean web) {
		this.web = web;
	}

	@Override
	public String toString() {
		return "Framework [id=" + id + ", name=" + name + ", language=" + language + ", description=" + description
				+ ", web=" + web + "]";
	}

}
