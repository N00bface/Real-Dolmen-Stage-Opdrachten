/*
 * Copyright (c) 2016. LICENCED BY (c) Jari Van Melckebeke, all rights reserved, use only with permission of Jari Van Melckebeke
 */

package org.jarivm.relationGraph.domains;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Jari Van Melckebeke
 * @since 20.09.16
 */
@Component
@NodeEntity
public class Client {
	@GraphId
	private Long id;
	@Relationship(type = "IsSectorFor", direction = Relationship.INCOMING)
	private Sector sector;
	private String name;
	private Long experience;
	@DateString(value = "yyyy-MM-dd")
	private Date dateOfFounding;
	@DateString(value = "yyyy-MM-dd")
	private Date dateOfFirstCooperation;

	public Client(String name) {
		this.name = name;
	}

	public Client() {

	}

	public Date getDateOfFirstCooperation() {
		return dateOfFirstCooperation;
	}

	public void setDateOfFirstCooperation(Date dateOfFirstCooperation) {
		this.dateOfFirstCooperation = dateOfFirstCooperation;
	}

	public Date getDateOfFounding() {
		return dateOfFounding;
	}

	public void setDateOfFounding(Date dateOfFounding) {
		this.dateOfFounding = dateOfFounding;
	}

	public Long getExperience() {
		return experience;
	}

	public void setExperience(Long experience) {
		this.experience = experience;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	@Override
	public String toString() {
		return "Client{" +
				"id=" + id +
				", sector=" + sector +
				", name='" + name + '\'' +
				", experience=" + experience +
				", dateOfFounding=" + dateOfFounding +
				", dateOfFirstCooperation=" + dateOfFirstCooperation +
				'}';
	}
}
