/*
 * Copyright (c) 2016. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.domains;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateString;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Jari Van Melckebeke
 * @since 30.09.16
 */
@Component
@RelationshipEntity(type = "Issued")
public class Issued {
	@GraphId
	private Long id;
	@StartNode
	private Client client;
	@EndNode
	private Project project;
	@DateString(value = "yyyy-MM-dd")
	private Date dateGiven;
	private Long nProjectForClient;

	public Issued(Client client, Project project, Date dateGiven, Long nProjectForClient) {
		this.client = client;
		this.project = project;
		this.dateGiven = dateGiven;
		this.nProjectForClient = nProjectForClient;
	}

	public Issued(Client client, Project project) {
		this.client = client;
		this.project = project;
	}

	public Issued() {

	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDateGiven() {
		return dateGiven;
	}

	public void setDateGiven(Date dateGiven) {
		this.dateGiven = dateGiven;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Long getnProjectForClient() {
		return nProjectForClient;
	}

	public void setnProjectForClient(Long nProjectForClient) {
		this.nProjectForClient = nProjectForClient;
	}

	@Override
	public String toString() {
		return "Issued{" +
				"id=" + id +
				", client=" + client +
				", dateGiven=" + dateGiven +
				", nProjectForClient=" + nProjectForClient +
				'}';
	}

}
