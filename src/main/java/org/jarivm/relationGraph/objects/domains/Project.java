package org.jarivm.relationGraph.objects.domains;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.Set;

/**
 * @author Jari Van Melckebeke
 * @since 20.09.16
 */
@NodeEntity
public class Project {
    @GraphId
    private Long id;

    private double cost;
    private String name;
    private Date dateStarted;
    private Date dateFinished;
    private String language;

    @Relationship(type = "WORKED_ON", direction = Relationship.INCOMING)
    private Set<Employee> team;

    @Relationship(type = "ISSUED", direction = Relationship.INCOMING)
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(Date dateFinished) {
        this.dateFinished = dateFinished;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<Employee> getTeam() {
        return team;
    }

    public void setTeam(Set<Employee> team) {
        this.team = team;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
