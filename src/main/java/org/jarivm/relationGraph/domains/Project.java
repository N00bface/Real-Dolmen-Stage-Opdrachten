package org.jarivm.relationGraph.domains;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;
import org.neo4j.ogm.utils.RelationshipUtils;
import org.springframework.expression.spel.ast.FloatLiteral;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Jari Van Melckebeke
 * @since 20.09.16
 */
@NodeEntity(label = "Project")
public class Project {

	@GraphId
	private Long id;

	private Float cost;
	private Float scoreFromClient;
	private String name;
	private String version;
	private String language;
	private Boolean onTime;
	@DateString(value = "yyyy-MM-dd")
	private Date dateStarted;
	@DateString(value = "yyyy-MM-dd")
	private Date dateFinished;
	@Relationship(direction = Relationship.INCOMING, type = "WorkedOn")
	private List<WorkedOn> workedOn;
	@Relationship(direction = Relationship.INCOMING, type = "Issued")
	private Client client;

	public Project(String name) {
		this.name = name;
	}

	public Project() {
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Date getDateFinished() {
		return dateFinished;
	}

	public void setDateFinished(Date dateFinished) {
		this.dateFinished = dateFinished;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOnTime() {
		return onTime;
	}

	public void setOnTime(Boolean onTime) {
		this.onTime = onTime;
	}

	public Float getScoreFromClient() {
		return scoreFromClient;
	}

	public void setScoreFromClient(Float scoreFromClient) {
		this.scoreFromClient = scoreFromClient;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<WorkedOn> getWorkedOn() {
		return workedOn;
	}

	public void setWorkedOn(List<WorkedOn> workedOn) {
		this.workedOn = workedOn;
	}

	@Override
	public String toString() {
		return "Project{" +
				"id=" + id +
				", cost=" + cost +
				", name='" + name + '\'' +
				", version='" + version + '\'' +
				", language='" + language + '\'' +
				", onTime=" + onTime +
				", dateStarted=" + dateStarted +
				", dateFinished=" + dateFinished +
				", workedOn=" + workedOn +
				", client=" + client +
				'}';
	}

	public Boolean isOnTime() {
		return onTime;

	}
}
