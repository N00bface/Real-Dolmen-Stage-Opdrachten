package org.jarivm.relationGraph.domains;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;

/**
 * @author Jari Van Melckebeke
 * @since 29.09.16
 */
@RelationshipEntity(type = "WorkedOn")
public class WorkedOn {
	@GraphId
	private Long id;
	@StartNode
	private Employee employee;
	@EndNode
	private Project project;
	private boolean onTime;
	private String role;
	private double score;
	private long projectNr;

	public WorkedOn() {

	}

	public WorkedOn(Employee employee, Project project, boolean onTime, String role, double score, long projectNr) {
		this.employee = employee;
		this.project = project;
		this.onTime = onTime;
		this.role = role;
		this.score = score;
		this.projectNr = projectNr;
	}

	public WorkedOn(Employee employee, Project project, String role) {
		this.employee = employee;
		this.project = project;
		this.role = role;
	}

	public WorkedOn(Employee employee, Project project) {
		this.employee = employee;
		this.project = project;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public long getProjectNr() {
		return projectNr;
	}

	public void setProjectNr(long projectNr) {
		this.projectNr = projectNr;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public boolean isOnTime() {
		return onTime;
	}

	public void setOnTime(boolean onTime) {
		this.onTime = onTime;
	}

	@Override
	public String toString() {
		return "WorkedOn{" +
				"id=" + id +
				", employee=" + employee +
				", onTime=" + onTime +
				", role='" + role + '\'' +
				", score=" + score +
				", projectNr=" + projectNr +
				'}';
	}
}
